package com.fortytwo.matthurd.kotlinpiscine.intra.api

import com.fortytwo.matthurd.kotlinpiscine.intra.api.models.IntraAccessToken
import com.fortytwo.matthurd.kotlinpiscine.intra.api.models.IntraUser
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IntraApiEndpoint {


    @POST("oauth/token")
    fun authenticate(@Body params: Map<String, String>): Flowable<IntraAccessToken>

    @GET("v2/users/{id}")
    fun getUser(@Path("id") id: String): Flowable<IntraUser>
}