package com.fortytwo.matthurd.kotlinpiscine.intra.api

import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface IntraApiEndpoint {

    @GET("users/{id}")
    fun getUser(@Path("username") username: String): Flowable<IntraUser>

    @POST("oauth/token")
    fun authenticate(@Body params: Map<String, Any>): Flowable<IntraAccessToken>
}