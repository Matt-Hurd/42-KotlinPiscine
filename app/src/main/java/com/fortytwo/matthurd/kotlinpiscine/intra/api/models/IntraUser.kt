package com.fortytwo.matthurd.kotlinpiscine.intra.api.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class IntraUser(
        @PrimaryKey var id: Int = 0,
        var email: String? = null,
        var login: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var url: String? = null,
        var phone: String? = null,
        var displayname: String? = null,
        var imageUrl: String? = null,
        var staff: Boolean? = null,
        var correctionPoint: Int? = null,
        var poolMonth: String? = null,
        var poolYear: String? = null,
        var location: String? = null,
        var wallet: Int? = null,
        var cursusUsers: RealmList<IntraCursusUser?>? = null,
        @Ignore var groups: List<Any?>? = null,
        @Ignore var projectsUsers: List<Any?>? = null,
        @Ignore var languagesUsers: List<Any?>? = null,
        @Ignore var achievements: List<Any?>? = null,
        @Ignore var titles: List<Any?>? = null,
        @Ignore var partnerships: List<Any?>? = null,
        @Ignore var patroned: List<Any?>? = null,
        @Ignore var patroning: List<Any?>? = null,
        @Ignore var expertisesUsers: List<Any?>? = null,
        @Ignore var campus: List<Any?>? = null
) : Serializable, RealmObject() {
    companion object {
        private const val serialVersionUID = 2L
    }
}
