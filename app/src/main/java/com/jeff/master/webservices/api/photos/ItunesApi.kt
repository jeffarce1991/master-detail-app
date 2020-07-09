package com.jeff.master.webservices.api.photos

import com.jeff.master.webservices.dto.PhotoDto
import com.jeff.master.webservices.dto.MediaResultsDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ItunesApi {

    @GET("search?term=star&amp;country=au&amp;media=movie&amp;all")
    fun loadTracks(): Single<Response<MediaResultsDto>>

    @GET("photos/{id}")
    fun loadPhotoById(@Path("id") id: Int): Single<Response<PhotoDto>>
}