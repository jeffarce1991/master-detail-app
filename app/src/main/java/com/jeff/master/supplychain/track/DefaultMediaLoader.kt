package com.jeff.master.supplychain.track

import com.jeff.master.database.local.Media
import com.jeff.master.main.mapper.MediaDtoToMediaMapper
import com.jeff.master.webservices.internet.RxInternet
import com.jeff.master.webservices.usecase.loader.MediaRemoteLoader
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class DefaultMediaLoader @Inject
constructor(private val remoteLoader: MediaRemoteLoader,
            //private val localLoader: PhotoLocalLoader,
            //private val localSaver: PhotoLocalSaver,
            private val rxInternet: RxInternet): MediaLoader{

    override fun loadAll(): Single<List<Media>> {
        return rxInternet.isConnected()
            .andThen(remoteLoader.loadAll())
            .flatMapObservable { list -> Observable.fromIterable(list.results) }
            .flatMap(MediaDtoToMediaMapper())
            .toList()
            //.flatMap { photos -> Single.fromObservable(localSaver.saveAll(photos)) }
            .flatMap { tracks -> Single.just(tracks) }
    }

}