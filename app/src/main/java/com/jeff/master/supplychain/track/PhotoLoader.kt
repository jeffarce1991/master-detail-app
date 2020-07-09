package com.jeff.master.supplychain.track

import com.jeff.master.database.local.Photo
import io.reactivex.Single

interface PhotoLoader {

    fun loadAll(): Single<List<Photo>>

    fun loadAllFromLocal(): Single<List<Photo>>
}