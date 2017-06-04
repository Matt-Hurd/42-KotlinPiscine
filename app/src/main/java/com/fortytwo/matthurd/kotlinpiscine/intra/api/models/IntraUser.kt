package com.fortytwo.matthurd.kotlinpiscine.intra.api.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class IntraUser(
        @JsonProperty("id") var id: Int?,
        @JsonProperty("email") var email: String?,
        @JsonProperty("login") var login: String?,
        @JsonProperty("first_name") var firstName: String?,
        @JsonProperty("last_name") var lastName: String?,
        @JsonProperty("url") var url: String?,
        @JsonProperty("phone") var phone: Any?,
        @JsonProperty("displayname") var displayname: String?,
        @JsonProperty("image_url") var imageUrl: String?,
        @JsonProperty("staff") var staff: Boolean?,
        @JsonProperty("correction_point") var correctionPoint: Int?,
        @JsonProperty("pool_month") var poolMonth: String?,
        @JsonProperty("pool_year") var poolYear: String?,
        @JsonProperty("location") var location: Any?,
        @JsonProperty("wallet") var wallet: Int?,
        @JsonProperty("groups") var groups: List<Any?>?,
        @JsonProperty("cursus_users") var cursusUsers: List<IntraCursusUser?>?,
        @JsonProperty("projects_users") var projectsUsers: List<Any?>?,
        @JsonProperty("languages_users") var languagesUsers: List<Any?>?,
        @JsonProperty("achievements") var achievements: List<Any?>?,
        @JsonProperty("titles") var titles: List<Any?>?,
        @JsonProperty("partnerships") var partnerships: List<Any?>?,
        @JsonProperty("patroned") var patroned: List<Any?>?,
        @JsonProperty("patroning") var patroning: List<Any?>?,
        @JsonProperty("expertises_users") var expertisesUsers: List<Any?>?,
        @JsonProperty("campus") var campus: List<Any?>?
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}
