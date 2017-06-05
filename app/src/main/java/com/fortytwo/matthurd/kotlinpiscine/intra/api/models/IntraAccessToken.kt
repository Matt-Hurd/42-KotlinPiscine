package com.fortytwo.matthurd.kotlinpiscine.intra.api.models

import io.realm.RealmObject
import java.io.Serializable

open class IntraAccessToken(
        var accessToken: String? = null,
        var createdAt: Long? = null,
        var expiresIn: Long? = null,
        var scope: String? = null,
        var tokenType: String? = null
)
    : Serializable, RealmObject() {
    companion object {
        private const val serialVersionUID = 2L
    }
}