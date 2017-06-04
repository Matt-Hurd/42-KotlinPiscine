package com.fortytwo.matthurd.kotlinpiscine

import android.app.Application;
import android.content.Context
import com.fortytwo.matthurd.kotlinpiscine.dagger.DaggerPiscineComponent
import com.fortytwo.matthurd.kotlinpiscine.dagger.PiscineComponent
import com.fortytwo.matthurd.kotlinpiscine.intra.api.IntraApiServerConfig
import com.fortytwo.matthurd.kotlinpiscine.intra.api.dagger.IntraApiModule

class PiscineApplication : Application() {

    lateinit var piscineComponent: PiscineComponent

    override fun onCreate() {
        super.onCreate()
        piscineComponent = DaggerPiscineComponent.builder()
                .intraApiModule(IntraApiModule(createIntraApiServerConfig()))
                .build()
    }

    private fun createIntraApiServerConfig(): IntraApiServerConfig {
        return IntraApiServerConfig(
                resources.getString(R.string.intra_base_url),
                resources.getString(R.string.intra_uid),
                resources.getString(R.string.intra_secret)
        )
    }

}