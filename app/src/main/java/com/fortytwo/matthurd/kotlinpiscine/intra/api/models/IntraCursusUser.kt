package com.fortytwo.matthurd.kotlinpiscine.intra.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class IntraCursusUser(
        @JsonProperty("begin_at") var beginAt: String?,
        @JsonProperty("cursus") var cursus: IntraCursus?,
        @JsonProperty("cursus_id") var cursusId: Long?,
        @JsonProperty("end_at") var endAt: Any?,
        @JsonProperty("grade") var grade: String?,
        @JsonProperty("id") var id: Long?,
        @JsonProperty("level") var level: Double?,
        @JsonProperty("skills") var skills: List<Any?>?,
        @JsonProperty("user") var user: IntraCursusUserUser?
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}

data class IntraCursusUserUser(
        @JsonProperty("id") var id: Long,
        @JsonProperty("login") var login: String,
        @JsonProperty("url") var url: String
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}