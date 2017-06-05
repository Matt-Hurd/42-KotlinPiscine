package com.fortytwo.matthurd.kotlinpiscine.intra.api.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class IntraProject(
        @PrimaryKey var id: Int = 0,
        var name: String? = null,
        var slug: String? = null,
        var description: String? = null,
        var parent: Int? = null,
        var tier: Int? = null,
        var createdAt: String? = null,
        var updatedAt: String? = null,
        var exam: Boolean? = null,
        var cursus: RealmList<IntraCursus?>? = null,
        @Ignore var objectives: List<String>? = null,
        @Ignore var children: List<Any>? = null,
        @Ignore var attachments: List<Any>? = null,
        @Ignore var campus: List<Any>? = null,
        @Ignore var skills: List<Any>? = null,
        @Ignore var videos: List<Any>? = null,
        @Ignore var tags: List<Any>? = null,
        @Ignore var projectSessions: List<Any>? = null
) : Serializable, RealmObject() {
    companion object {
        const val serialVersionUID = 2L
    }
}