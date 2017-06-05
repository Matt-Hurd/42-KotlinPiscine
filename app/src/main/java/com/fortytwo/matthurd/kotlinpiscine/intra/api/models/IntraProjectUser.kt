package com.fortytwo.matthurd.kotlinpiscine.intra.api.models

import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class IntraProjectUser(
        @PrimaryKey var id: Int? = null,
        var occurrence: Int? = null,
        var finalMark: Int? = null,
        var status: String? = null,
        var varidated: Boolean? = null,
        var currentTeamId: Int? = null,
        var project: IntraProject? = null,
        @Ignore var cursusIds: List<Int>? = null
) : Serializable, RealmObject() {
    companion object {
        private const val serialVersionUID = 2L
    }
}