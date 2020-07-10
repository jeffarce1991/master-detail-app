package com.jeff.master.database.usecase.local.loader

import com.jeff.master.database.local.Media
import com.jeff.master.database.local.Photo
import io.reactivex.Single

interface MediaLocalLoader {
    fun loadAll(): Single<List<Media>>
    fun loadById(id: Int): Single<Media>
}