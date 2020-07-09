package com.jeff.master.database.usecase.local.loader

import com.jeff.master.database.local.Photo
import io.reactivex.Single

interface PhotoLocalLoader {
    fun loadAll(): Single<List<Photo>>
}