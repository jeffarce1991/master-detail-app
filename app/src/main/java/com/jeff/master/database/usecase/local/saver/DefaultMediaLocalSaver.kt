package com.jeff.master.database.usecase.local.saver

import com.jeff.master.database.local.Media
import com.jeff.master.database.local.Photo
import com.jeff.master.database.room.dao.MediaDao
import com.jeff.master.database.room.dao.PhotoDao
import io.reactivex.Completable
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class DefaultMediaLocalSaver @Inject
constructor(private val dao: MediaDao) : MediaLocalSaver {

    override fun save(media: Media): Completable {
        return Completable.fromAction { dao.insert(media)}
    }

    override fun saveAll(mediaList: List<Media>): Observable<List<Media>> {
        return Completable.fromCallable {
            dao.insert(mediaList)
            Timber.d("==q saveAll Done")
            Completable.complete()
        }.andThen(Observable.fromCallable { mediaList })
    }

}