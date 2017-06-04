package com.fortytwo.matthurd.kotlinpiscine.intra.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class IntraUser(
        @JsonProperty("id") var id: Int = 0,
        @JsonProperty("email") var email: String? = null,
        @JsonProperty("login") var login: String? = null,
        @JsonProperty("first_name") var firstName: String? = null,
        @JsonProperty("last_name") var lastName: String? = null,
        @JsonProperty("url") var url: String? = null,
        @JsonProperty("phone") var phone: Any? = null,
        @JsonProperty("displayname") var displayname: String? = null,
        @JsonProperty("image_url") var imageUrl: String? = null,
        @JsonProperty("staff?") var staff: Boolean = false,
        @JsonProperty("correction_point") var correctionPoint: Int = 0,
        @JsonProperty("pool_month") var poolMonth: String? = null,
        @JsonProperty("pool_year") var poolYear: String? = null,
        @JsonProperty("location") var location: Any? = null,
        @JsonProperty("wallet") var wallet: Int = 0,
        @JsonProperty("groups") var groups: List<Any>? = null,
        @JsonProperty("cursus_users") var cursusUsers: List<Any>? = null,
        @JsonProperty("projects_users") var projectsUsers: List<Any>? = null,
        @JsonProperty("languages_users") var languagesUsers: List<Any>? = null,
        @JsonProperty("achievements") var achievements: List<Any>? = null,
        @JsonProperty("titles") var titles: List<Any>? = null,
        @JsonProperty("partnerships") var partnerships: List<Any>? = null,
        @JsonProperty("patroned") var patroned: List<Any>? = null,
        @JsonProperty("patroning") var patroning: List<Any>? = null,
        @JsonProperty("expertises_users") var expertisesUsers: List<Any>? = null,
        @JsonProperty("campus") var campus: List<Any>? = null
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}
