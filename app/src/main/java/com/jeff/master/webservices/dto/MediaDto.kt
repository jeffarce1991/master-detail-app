package com.jeff.master.webservices.dto

import com.google.gson.annotations.SerializedName

class MediaDto(
    @field:SerializedName("wrapperType") var wrapperType: String,
    @field:SerializedName("kind") var kind: String,
    @field:SerializedName("artistId") var artistId: Int,
    @field:SerializedName("trackId") var trackId: Int,
    @field:SerializedName("artistName") var artistName: String,
    @field:SerializedName("trackName") var trackName: String? = "",
    @field:SerializedName("collectionName") var collectionName: String,
    @field:SerializedName("trackViewUrl") var trackViewUrl: String,
    @field:SerializedName("previewUrl") var previewUrl: String,
    @field:SerializedName("artworkUrl30") var artworkUrl30: String,
    @field:SerializedName("artworkUrl60") var artworkUrl60: String,
    @field:SerializedName("artworkUrl100") var artworkUrl100: String,
    @field:SerializedName("trackPrice") var trackPrice: Double,
    @field:SerializedName("trackHdPrice") var trackHdPrice: Double,
    @field:SerializedName("releaseDate") var releaseDate: String,
    @field:SerializedName("country") var country: String,
    @field:SerializedName("currency") var currency: String,
    @field:SerializedName("primaryGenreName") var primaryGenreName: String,
    @field:SerializedName("contentAdvisoryRating") var contentAdvisoryRating: String,
    @field:SerializedName("shortDescription") var shortDescription: String,
    @field:SerializedName("longDescription") var longDescription: String,
    @field:SerializedName("description") var description: String
)