package com.jeff.master.supplychain.track

import com.jeff.master.database.local.Media
import io.reactivex.Single

interface MediaLoader {

    fun loadAll(): Single<List<Media>>
    fun loadById(id: Int): Single<Media>
}