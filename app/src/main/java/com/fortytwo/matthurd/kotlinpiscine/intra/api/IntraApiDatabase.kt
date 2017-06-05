package com.fortytwo.matthurd.kotlinpiscine.intra.api

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import com.fortytwo.matthurd.kotlinpiscine.intra.api.dao.IntraUserDao
import com.fortytwo.matthurd.kotlinpiscine.intra.api.models.IntraUser




@TypeConverters(Converter::class)
@Database(entities = arrayOf(IntraUser::class), version = 1)
abstract class IntraApiDatabase : RoomDatabase() {
    abstract fun intraUserDao(): IntraUserDao
}

class Converter{
    @TypeConverter
    fun fromAny(value: Any): String {
        return value.toString()
    }
}