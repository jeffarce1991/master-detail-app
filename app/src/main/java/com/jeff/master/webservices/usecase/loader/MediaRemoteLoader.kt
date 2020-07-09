package com.jeff.master.webservices.usecase.loader

import com.jeff.master.webservices.dto.MediaResultsDto
import io.reactivex.Single

interface MediaRemoteLoader {

    fun loadAll(): Single<MediaResultsDto>

    fun loadById(id: Int): Single<MediaResultsDto>
}