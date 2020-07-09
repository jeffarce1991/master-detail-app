package com.jeff.master.main.mapper

import com.jeff.master.Constants.MediaWrapperTypes.AUDIOBOOK
import com.jeff.master.Constants.MediaWrapperTypes.TRACK
import com.jeff.master.Constants.TrackKinds.FEATURE_MOVIE
import com.jeff.master.database.local.Media
import com.jeff.master.webservices.dto.MediaDto
import io.reactivex.Observable
import io.reactivex.functions.Function

class MediaDtoToMediaMapper : Function<MediaDto, Observable<Media>> {

    @Throws(Exception::class)
    override fun apply(dto: MediaDto): Observable<Media> {
        return Observable.fromCallable {
            val media = Media()
            if (dto.wrapperType == TRACK) {
                media.id = dto.trackId
                media.trackName = dto.trackName!!
                media.artistName = dto.artistName
            } else if (dto.wrapperType == AUDIOBOOK) {
                media.id = dto.collectionId
                media.trackName = dto.collectionName
                media.artistName = dto.artistName
                media.shortDescription = dto.description
                media.longDescription = dto.description
            }

            media.artWorkUrl = dto.artworkUrl100
            media.currency = dto.currency
            media.price = dto.trackPrice
            media.genre = dto.primaryGenreName

            if (dto.kind == FEATURE_MOVIE) {
                media.hdPrice = dto.trackHdPrice
                media.shortDescription = dto.shortDescription
                media.longDescription = dto.longDescription
            } else {
                //media.shortDescription = ""
                //media.longDescription = ""
            }

            media
        }
    }
}