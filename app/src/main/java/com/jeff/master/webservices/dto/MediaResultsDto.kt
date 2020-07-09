package com.jeff.master.webservices.dto

import com.google.gson.annotations.SerializedName

data class MediaResultsDto(
    @field:SerializedName("resultCount") var resultCount: Int,
    @field:SerializedName("results") var results: List<MediaDto>
)