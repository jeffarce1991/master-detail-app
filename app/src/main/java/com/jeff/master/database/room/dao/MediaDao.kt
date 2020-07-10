package com.jeff.master.database.room.dao

import androidx.room.*
import com.jeff.master.database.local.Media

@Dao
interface MediaDao {
    @Query("Select * FROM " + Media.TABLE_NAME)
    fun loadAll(): List<Media>

    @Query("Select * FROM " + Media.TABLE_NAME +
            " WHERE "+ Media.COLUMN_ID +" IN (:id)")
    fun loadAllByIds(id: IntArray): List<Media>

    @Query("SELECT * FROM " + Media.TABLE_NAME +
            " WHERE id LIKE :id AND id LIMIT 1")
    fun findById(id: String): Media

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mediaList: List<Media>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(media: Media)

    @Delete
    fun delete(media: Media)

}