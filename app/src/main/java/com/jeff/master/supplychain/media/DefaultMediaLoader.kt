package com.jeff.master.supplychain.media

import com.jeff.master.database.local.Media
import com.jeff.master.database.usecase.local.loader.MediaLocalLoader
import com.jeff.master.database.usecase.local.saver.MediaLocalSaver
import com.jeff.master.main.mapper.MediaDtoToMediaMapper
import com.jeff.master.utilities.exception.EmptyResultException
import com.jeff.master.webservices.internet.RxInternet
import com.jeff.master.webservices.usecase.loader.MediaRemoteLoader
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class DefaultMediaLoader @Inject
constructor(private val remoteLoader: MediaRemoteLoader,
            private val localLoader: MediaLocalLoader,
            private val localSaver: MediaLocalSaver,
            private val rxInternet: RxInternet): MediaLoader{

    //Load all data from remote else Load all from local
    override fun loadAll(): Single<List<Media>> {
        return loadAllRemotely()
            .onErrorResumeNext { loadAllLocally() }
    }

    override fun loadAllRemotely(): Single<List<Media>> {
        return rxInternet.isConnected()
            .andThen(remoteLoader.loadAll())
            .flatMapObservable { list -> Observable.fromIterable(list.results) }
            .flatMap(MediaDtoToMediaMapper())
            .toList()
            .flatMap { photos -> Single.fromObservable(localSaver.saveAll(photos)) }
            .flatMap { tracks -> Single.just(tracks) }
    }

    override fun loadAllLocally(): Single<List<Media>> {
        return localLoader.loadAll()
            .flatMap { Single.just(it) }
            /*.flatMap { mediaList ->
                if (mediaList.isEmpty()) {
                    Single.error(Throwable(EmptyResultException()))
                } else {
                    Single.just(mediaList)
                }
            }*/
    }

    override fun loadByIdRemotely(id: Int): Single<Media> {
        return rxInternet.isConnected()
            .andThen(remoteLoader.loadById(id))
            .flatMapObservable { list -> Observable.fromIterable(list.results) }
            .flatMap(MediaDtoToMediaMapper())
            .toList()
            //.flatMap { photos -> Single.fromObservable(localSaver.saveAll(photos)) }
            .flatMap { tracks -> Single.just(tracks[0]) }
    }


    override fun loadByIdLocally(id: Int): Single<Media> {
        return localLoader.loadById(id)
    }

}