package com.fortytwo.matthurd.kotlinpiscine.intra.api.dagger

import com.fortytwo.matthurd.kotlinpiscine.intra.IntraActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(IntraApiModule::class))
interface IntraApiServerComponent {
    fun inject(activity: IntraActivity)
}