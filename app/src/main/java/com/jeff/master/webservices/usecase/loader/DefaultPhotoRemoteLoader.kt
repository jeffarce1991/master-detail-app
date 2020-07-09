package com.jeff.master.webservices.usecase.loader

import com.jeff.master.webservices.api.ApiFactory
import com.jeff.master.webservices.api.photos.PhotosApi
import com.jeff.master.webservices.dto.PhotoDto
import com.jeff.master.webservices.transformer.ResponseCodeNot200SingleTransformer
import io.reactivex.Single
import javax.inject.Inject

class DefaultPhotoRemoteLoader @Inject
constructor(private val apiFactory: ApiFactory): PhotoRemoteLoader {

    override fun loadAll(): Single<List<PhotoDto>> {
        return apiFactory.create(PhotosApi::class.java)
            .flatMap { it.loadPhotos() }
            .compose(ResponseCodeNot200SingleTransformer())
            .flatMap { response ->
                Single.just(response.body()!!) }
    }
}