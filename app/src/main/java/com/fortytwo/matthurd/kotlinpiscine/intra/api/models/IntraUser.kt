package com.fortytwo.matthurd.kotlinpiscine.intra.api.models

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.ColumnInfo
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
data class IntraUser(
        @PrimaryKey @ColumnInfo(name = "id") @JsonProperty("id") var id: Int,
        @ColumnInfo(name = "email") @JsonProperty("email") var email: String? = null,
        @ColumnInfo(name = "login") @JsonProperty("login") var login: String? = null,
        @ColumnInfo(name = "first_name") @JsonProperty("first_name") var firstName: String? = null,
        @ColumnInfo(name = "last_name") @JsonProperty("last_name") var lastName: String? = null,
        @ColumnInfo(name = "url") @JsonProperty("url") var url: String? = null,
        @ColumnInfo(name = "phone") @JsonProperty("phone") var phone: String? = null,
        @ColumnInfo(name = "displayname") @JsonProperty("displayname") var displayname: String? = null,
        @ColumnInfo(name = "image_url") @JsonProperty("image_url") var imageUrl: String? = null,
        @ColumnInfo(name = "staff") @JsonProperty("staff") var staff: Boolean? = null,
        @ColumnInfo(name = "correction_point") @JsonProperty("correction_point") var correctionPoint: Int? = null,
        @ColumnInfo(name = "pool_month") @JsonProperty("pool_month") var poolMonth: String? = null,
        @ColumnInfo(name = "pool_year") @JsonProperty("pool_year") var poolYear: String? = null,
        @ColumnInfo(name = "location") @JsonProperty("location") var location: String? = null,
        @ColumnInfo(name = "wallet") @JsonProperty("wallet") var wallet: Int? = null,
        @Embedded @JsonProperty("cursus_users") var cursusUsers: List<IntraCursusUser?>? = null
//        @ColumnInfo(name = "groups") @JsonProperty("groups") var groups: List<Any?>?,
//        @ColumnInfo(name = "projects_users") @JsonProperty("projects_users") var projectsUsers: List<Any?>?,
//        @ColumnInfo(name = "languages_users") @JsonProperty("languages_users") var languagesUsers: List<Any?>?,
//        @ColumnInfo(name = "achievements") @JsonProperty("achievements") var achievements: List<Any?>?,
//        @ColumnInfo(name = "titles") @JsonProperty("titles") var titles: List<Any?>?,
//        @ColumnInfo(name = "partnerships") @JsonProperty("partnerships") var partnerships: List<Any?>?,
//        @ColumnInfo(name = "patroned") @JsonProperty("patroned") var patroned: List<Any?>?,
//        @ColumnInfo(name = "patroning") @JsonProperty("patroning") var patroning: List<Any?>?,
//        @ColumnInfo(name = "expertises_users") @JsonProperty("expertises_users") var expertisesUsers: List<Any?>?,
//        @ColumnInfo(name = "campus") @JsonProperty("campus") var campus: List<Any?>?
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}
