package com.fortytwo.matthurd.kotlinpiscine.intra.api

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class IntraApiModule(internal var mBaseUrl: String) {
    @Provides
    @Singleton
    internal fun provideIntraApiServer(config: IntraApiServerConfig): IntraApiServer {
        return IntraApiServer(config)
    }
}