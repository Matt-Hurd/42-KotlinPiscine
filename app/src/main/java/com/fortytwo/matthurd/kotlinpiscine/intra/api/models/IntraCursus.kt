package com.fortytwo.matthurd.kotlinpiscine.intra.api.models

import io.realm.RealmObject
import java.io.Serializable

open class IntraCursus(
         var createdAt: String? = null,
         var id: Long? = null,
         var name: String? = null,
         var slug: String? = null
) : Serializable, RealmObject() {
    companion object {
        private const val serialVersionUID = 2L
    }
}