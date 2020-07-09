package com.jeff.master.webservices.api.itunes

import com.jeff.master.webservices.dto.MediaResultsDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ItunesApi {

    @GET("search?term=star&amp;country=au&amp;media=movie&amp;all")
    fun loadTracks(): Single<Response<MediaResultsDto>>

    @GET("lookup")
    fun loadById(@Query("id") id: Int): Single<Response<MediaResultsDto>>
}