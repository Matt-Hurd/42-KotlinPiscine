package com.fortytwo.matthurd.kotlinpiscine.intra.api.dagger

import com.fortytwo.matthurd.kotlinpiscine.intra.api.IntraApiServer
import com.fortytwo.matthurd.kotlinpiscine.intra.api.IntraApiServerConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class IntraApiModule(internal var config: IntraApiServerConfig) {
    @Provides
    @Singleton
    internal fun provideIntraApiServer(): IntraApiServer {
        return IntraApiServer(config)
    }
}