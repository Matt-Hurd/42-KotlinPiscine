package com.fortytwo.matthurd.kotlinpiscine.intra.api.models

import android.arch.persistence.room.Entity
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@Entity
data class IntraCursus(
        @JsonProperty("created_at") var createdAt: String,
        @JsonProperty("id") var id: Long,
        @JsonProperty("name") var name: String,
        @JsonProperty("slug") var slug: String
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}
