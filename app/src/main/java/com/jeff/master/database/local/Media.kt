package com.jeff.master.database.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Media.TABLE_NAME)
data class Media constructor(
    @ColumnInfo(name = "id")
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "track_name")
                 var trackName: String,
    @ColumnInfo(name = "artist_name")
                 var artistName: String,
    @ColumnInfo(name = "art_work")
                 var artWorkUrl: String,
    @ColumnInfo(name = "currency")
                 var currency: String,
    @ColumnInfo(name = "price")
                 var price: Double,
    @ColumnInfo(name = "hd_price")
                 var hdPrice: Double? = null,
    @ColumnInfo(name = "genre")
                 var genre: String,
    @ColumnInfo(name = "short_description")
                 var shortDescription: String? = null,
    @ColumnInfo(name = "long_description")
                 var longDescription: String? = null) {

    constructor(): this(0, "", "","", "", 0.0,
        0.0, "", "","")
    companion object {

        const val COLUMN_DEAL_ID = "media_id"
        const val COLUMN_ID = "id"
        const val TABLE_NAME = "medias"
    }
}
