package com.jeff.master.webservices.usecase.loader

import com.jeff.master.webservices.api.ApiFactory
import com.jeff.master.webservices.api.itunes.ItunesApi
import com.jeff.master.webservices.dto.MediaResultsDto
import com.jeff.master.webservices.transformer.NullResultTransformer
import com.jeff.master.webservices.transformer.ResponseCodeNot200SingleTransformer
import io.reactivex.Single
import javax.inject.Inject

class DefaultMediaRemoteLoader @Inject
constructor(private val apiFactory: ApiFactory): MediaRemoteLoader {

    override fun loadAll(): Single<MediaResultsDto> {
        return apiFactory.create(ItunesApi::class.java)
            .flatMap { it.loadTracks() }
            .compose(ResponseCodeNot200SingleTransformer())
            .compose(NullResultTransformer())
            .flatMap { response ->
                Single.just(response.body()!!) }
    }

    override fun loadById(id: Int): Single<MediaResultsDto> {
        return apiFactory.create(ItunesApi::class.java)
            .flatMap { it.loadById(id) }
            .compose(ResponseCodeNot200SingleTransformer())
            .compose(NullResultTransformer())
            .flatMap { response ->
                Single.just(response.body()!!) }
    }
}