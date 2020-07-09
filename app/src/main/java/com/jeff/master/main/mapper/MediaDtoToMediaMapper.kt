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
    override fun apply(mediaDto: MediaDto): Observable<Media> {
        return Observable.fromCallable {
            val media = Media()
            if (mediaDto.wrapperType == TRACK) {
                media.id = mediaDto.trackId
                media.trackName = mediaDto.trackName!!
                media.artistName = mediaDto.artistName
            } else if (mediaDto.wrapperType == AUDIOBOOK) {
                media.id = mediaDto.artistId
                media.trackName = mediaDto.collectionName
                media.artistName = mediaDto.artistName
            }

            media.artWorkUrl = mediaDto.artworkUrl100
            media.currency = mediaDto.currency
            media.price = mediaDto.trackPrice
            media.genre = mediaDto.primaryGenreName

            if (mediaDto.kind == FEATURE_MOVIE) {
                media.hdPrice = mediaDto.trackHdPrice
            }

            media
        }
    }
}