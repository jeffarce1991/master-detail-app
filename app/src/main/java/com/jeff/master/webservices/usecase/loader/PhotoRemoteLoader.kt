package com.jeff.master.webservices.usecase.loader

import com.jeff.master.webservices.dto.PhotoDto
import io.reactivex.Single

interface PhotoRemoteLoader {

    fun loadAll(): Single<List<PhotoDto>>
}