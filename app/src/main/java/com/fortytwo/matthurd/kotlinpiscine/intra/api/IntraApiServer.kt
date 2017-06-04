package com.fortytwo.matthurd.kotlinpiscine.intra.api

import android.util.Log
import io.reactivex.Completable
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


val AUTHORIZATION = "access_token"

class IntraApiServer(config: IntraApiServerConfig, authEnabled: Boolean = true) {
    var apiServer: IntraApiEndpoint

    init {
        val rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
        val retrofitBuilder = Retrofit.Builder()
                .baseUrl(config.baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
        if (authEnabled) {
            retrofitBuilder.client(
                    OkHttpClient()
                            .newBuilder()
                            .authenticator(
                                    IntraTokenAuthenticator(config,
                                            IntraApiServer(config, false)
                                    ))
                            .readTimeout(5, TimeUnit.SECONDS)
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .build())
        }
        apiServer = retrofitBuilder.build().create(IntraApiEndpoint::class.java)
    }
}

class IntraTokenAuthenticator(var config: IntraApiServerConfig, var intraAuthServer: IntraApiServer) : Authenticator {
    @Throws(IOException::class)
    override fun authenticate(route: Route, response: Response): Request {
        val params = mapOf(Pair("client_id", config.uid), Pair("client_secret", config.secret))
        var newToken = intraAuthServer
                .apiServer
                .authenticate(params)
                .doOnError(
                        {
                            Log.e("Auth", "Authentication failed")
                        }
                )
                .blockingFirst()
        Log.i("ayy", newToken.tokenType + " " + newToken.accessToken)
        return response.request().newBuilder()
                .header("Authorization", newToken.tokenType + " " + newToken.accessToken)
                .header("Users-Agent", "GuessWho")
                .header("Accept", "application/json")
                .build();

    }
}