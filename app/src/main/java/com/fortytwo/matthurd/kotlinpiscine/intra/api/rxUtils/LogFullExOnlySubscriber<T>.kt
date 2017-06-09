package com.fortytwo.matthurd.kotlinpiscine.intra.api.rxUtils

import android.content.Context
import android.util.Log
import io.reactivex.subscribers.DisposableSubscriber

open class ISLogFullExOnlySubscriber<T>(context: Context? = null) : DisposableSubscriber<T>() {

    private val TAG: String = context?.javaClass?.simpleName ?: "Rx"

    override fun onComplete() {}

    override fun onError(ex: Throwable) {
        val errorMessage = ex.localizedMessage + " onError of ISLogFullExOnlySubscriber"
        Log.w(TAG, errorMessage)
    }

    override fun onNext(t: T) {}
}