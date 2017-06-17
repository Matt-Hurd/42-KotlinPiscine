package com.fortytwo.matthurd.kotlinpiscine.intra.api.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class IntraCursus(
        @PrimaryKey
        var id: Long? = null,
        var createdAt: String? = null,
        var name: String? = null,
        var slug: String? = null
) : Serializable, RealmObject() {
    companion object {
        private const val serialVersionUID = 2L
    }
}