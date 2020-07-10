package com.jeff.master

class Constants private constructor() {
    object Gateways {
        const val ITUNES = "https://itunes.apple.com"
    }

    object TrackKinds {
        const val FEATURE_MOVIE = "feature-movie"
        const val SONG = "song"
    }
    object MediaWrapperTypes {
        const val TRACK = "track"
        const val AUDIOBOOK = "audiobook"
    }
}