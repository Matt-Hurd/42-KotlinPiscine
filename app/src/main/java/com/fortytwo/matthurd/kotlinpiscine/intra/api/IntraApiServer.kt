package com.fortytwo.matthurd.kotlinpiscine.intra.api

import android.util.Log
import com.fortytwo.matthurd.kotlinpiscine.intra.api.models.IntraAccessToken
import com.google.gson.*
import io.reactivex.rxkotlin.blockingSubscribeBy
import retrofit2.Retrofit
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmObject
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

class IntraApiServer(config: IntraApiServerConfig, authEnabled: Boolean = true) {
    var apiServer: IntraApiEndpoint
    var realm: Realm = Realm.getDefaultInstance()

    init {
        val okHttpClient = createOkHttpInstance(config, authEnabled)
        val rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
        apiServer = Retrofit.Builder()
                .baseUrl(config.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(createGsonInstance()))
                .addCallAdapterFactory(rxAdapter)
                .client(okHttpClient)
                .build()
                .create(IntraApiEndpoint::class.java)
    }

    private fun createOkHttpInstance(config: IntraApiServerConfig, authEnabled: Boolean): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient()
                .newBuilder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
        if (authEnabled) {
            okHttpClient.authenticator(IntraTokenAuthenticator(config, IntraApiServer(config, false)))
        }
        return okHttpClient.build()
    }

    private fun createGsonInstance(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setExclusionStrategies(object : ExclusionStrategy {
                    override fun shouldSkipField(f: FieldAttributes): Boolean {
                        return f.declaringClass == RealmObject::class.java
                    }

                    override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                        return false
                    }
                }).create()
    }
}

class IntraTokenAuthenticator(var config: IntraApiServerConfig, var intraAuthServer: IntraApiServer) : Authenticator {
    @Throws(IOException::class)
    override fun authenticate(route: Route, response: Response): Request {
        val params = mapOf(
                "grant_type" to "client_credentials",
                "client_id" to config.uid,
                "client_secret" to config.secret)
        var newToken: IntraAccessToken? = null
        intraAuthServer
                .apiServer
                .authenticate(params)
                .blockingSubscribeBy(
                        onError = { throwable -> Log.e("Auth", "Authentication failed: " + throwable.message) },
                        onNext = {
                            intraAccessToken ->
                            newToken = intraAccessToken
                        })

        return response.request().newBuilder()
                .header("Authorization", newToken?.tokenType + " " + newToken?.accessToken)
                .header("Users-Agent", "GuessWho")
                .header("Accept", "application/json")
                .build()

    }
}