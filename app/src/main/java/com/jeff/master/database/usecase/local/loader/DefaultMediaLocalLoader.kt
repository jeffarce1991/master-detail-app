package com.jeff.master.database.usecase.local.loader

import com.jeff.master.database.local.Media
import com.jeff.master.database.room.dao.MediaDao
import io.reactivex.Single
import javax.inject.Inject

class DefaultMediaLocalLoader @Inject
constructor(private val dao: MediaDao): MediaLocalLoader {

    override fun loadAll(): Single<List<Media>> {
        return Single.fromCallable { dao.loadAll() }
    }


    override fun loadById(id: Int): Single<Media> {
        return Single.fromCallable { dao.findById(id.toString()) }
    }
}