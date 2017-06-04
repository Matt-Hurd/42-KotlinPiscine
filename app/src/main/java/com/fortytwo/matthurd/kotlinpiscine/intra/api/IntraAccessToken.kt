package com.fortytwo.matthurd.kotlinpiscine.intra.api

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class IntraAccessToken(
        @JsonProperty("access_token") var accessToken: String,
        @JsonProperty("created_at") var createdAt: Long,
        @JsonProperty("expires_in") var expiresIn: Long,
        @JsonProperty("scope") var scope: String,
        @JsonProperty("token_type") var tokenType: String)
    : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}