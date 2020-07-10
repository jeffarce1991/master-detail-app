package com.jeff.master.supplychain.media

import com.jeff.master.database.local.Media
import io.reactivex.Single

interface MediaLoader {
    fun loadAll(): Single<List<Media>>

    fun loadAllRemotely(): Single<List<Media>>
    fun loadByIdRemotely(id: Int): Single<Media>


    fun loadAllLocally(): Single<List<Media>>
    fun loadByIdLocally(id: Int): Single<Media>
}