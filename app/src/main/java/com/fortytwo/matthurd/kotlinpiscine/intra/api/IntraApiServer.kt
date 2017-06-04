package com.fortytwo.matthurd.kotlinpiscine.intra.api

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class IntraApiServer(config: IntraApiServerConfig) {
    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(config.baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
    }
}