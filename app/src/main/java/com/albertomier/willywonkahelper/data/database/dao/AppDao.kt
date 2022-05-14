package com.albertomier.willywonkahelper.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.albertomier.willywonkahelper.data.database.entities.OompaLoompaEntity

@Dao
interface AppDao {
    @Query("SELECT * FROM oompa_loompa_table ORDER BY id DESC")
    suspend fun getOompaLoompas(): List<OompaLoompaEntity>

    @Query("SELECT * FROM oompa_loompa_table WHERE id = :oompaLoompaId")
    suspend fun getOompaLoompaById(oompaLoompaId: Int): OompaLoompaEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes:List<OompaLoompaEntity>)

    @Query("DELETE FROM oompa_loompa_table")
    suspend fun deleteAll()
}