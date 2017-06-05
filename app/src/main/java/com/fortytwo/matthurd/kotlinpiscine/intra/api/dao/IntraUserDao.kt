package com.fortytwo.matthurd.kotlinpiscine.intra.api.dao

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.fortytwo.matthurd.kotlinpiscine.intra.api.models.IntraUser


@Dao
interface IntraUserDao {
    @get:Query("SELECT * FROM intraUser")
    val all: List<IntraUser>

    @Query("SELECT * FROM intraUser WHERE id IN (:p0)")
    fun loadAllByIds(intraUserIds: IntArray): List<IntraUser>

    @Query("SELECT * FROM intraUser WHERE login LIKE :p0 LIMIT 1")
    fun findByLogin(login: String): IntraUser

    @Query("SELECT * FROM intraUser WHERE id IN (:p0)")
    fun loadAllByLogins(intraUserLoginList: List<String>): List<IntraUser>

    @Insert
    fun insertAll(vararg intraUsers: IntraUser)

    @Delete
    fun delete(intraUser: IntraUser)
}