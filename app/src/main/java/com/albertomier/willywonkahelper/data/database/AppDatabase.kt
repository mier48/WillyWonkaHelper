package com.albertomier.willywonkahelper.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.albertomier.willywonkahelper.data.database.dao.AppDao
import com.albertomier.willywonkahelper.data.database.entities.OompaLoompaEntity

@Database(entities = [OompaLoompaEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAppDao(): AppDao
}