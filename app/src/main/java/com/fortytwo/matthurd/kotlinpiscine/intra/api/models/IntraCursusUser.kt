package com.fortytwo.matthurd.kotlinpiscine.intra.api.models

import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class IntraCursusUser(
        @PrimaryKey
        var id: Long? = null,
        var beginAt: String? = null,
        var cursus: IntraCursus? = null,
        var cursusId: Long? = null,
        var endAt: String? = null,
        var grade: String? = null,
        var level: Double? = null,
        @Ignore var skills: List<Any?>? = null,
        @Ignore var user: IntraCursusUser? = null
) : Serializable, RealmObject() {
    companion object {
        private const val serialVersionUID = 2L
    }
}