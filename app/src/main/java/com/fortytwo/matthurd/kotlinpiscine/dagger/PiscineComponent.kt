package com.fortytwo.matthurd.kotlinpiscine.dagger

import com.fortytwo.matthurd.kotlinpiscine.intra.IntraActivity
import com.fortytwo.matthurd.kotlinpiscine.intra.api.dagger.IntraApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        IntraApiModule::class
))
interface PiscineComponent {
    fun inject(activity: IntraActivity)
}