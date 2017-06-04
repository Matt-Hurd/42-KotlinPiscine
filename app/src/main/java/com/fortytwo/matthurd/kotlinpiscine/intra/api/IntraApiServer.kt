package com.fortytwo.matthurd.kotlinpiscine.intra.api

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


class IntraApiServer(config: IntraApiServerConfig) {
    var retrofit: Retrofit

    init {
        val rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
        retrofit = Retrofit.Builder()
                .baseUrl(config.baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build()
    }
}