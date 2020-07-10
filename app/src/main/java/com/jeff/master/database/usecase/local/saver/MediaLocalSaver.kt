package com.jeff.master.database.usecase.local.saver

import com.jeff.master.database.local.Media
import io.reactivex.Completable
import io.reactivex.Observable

interface MediaLocalSaver {

    fun save(media: Media): Completable

    fun saveAll(mediaList: List<Media>): Observable<List<Media>>
}
