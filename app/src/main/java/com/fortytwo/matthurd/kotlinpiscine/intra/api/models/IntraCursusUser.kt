package com.fortytwo.matthurd.kotlinpiscine.intra.api.models

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.ColumnInfo
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@Entity
data class IntraCursusUser(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "begin_at") @JsonProperty("begin_at") var beginAt: String?,
        @ColumnInfo(name = "cursus") @JsonProperty("cursus") var cursus: IntraCursus?,
        @ColumnInfo(name = "cursus_id") @JsonProperty("cursus_id") var cursusId: Long?,
        @ColumnInfo(name = "end_at") @JsonProperty("end_at") var endAt: Any?,
        @ColumnInfo(name = "grade") @JsonProperty("grade") var grade: String?,
        @ColumnInfo(name = "id") @JsonProperty("id") var id: Long?,
        @ColumnInfo(name = "level") @JsonProperty("level") var level: Double?,
        @ColumnInfo(name = "skills") @JsonProperty("skills") var skills: List<Any?>?,
        @Embedded @JsonProperty("user") var user: IntraCursusUserUser?
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