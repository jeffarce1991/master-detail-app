package com.jeff.master

class Constants private constructor() {
    object Gateways {
        const val COVID19API = "https://api.covid19api.com"
        const val JSONPLACEHOLDER = "https://jsonplaceholder.typicode.com"
        const val ITUNES = "https://itunes.apple.com"
    }
    object DaoExceptions {
        const val ERROR_EMPTY_RESULT = "Empty results[] from DAO request"
        const val ERROR_NULL_RESULT = "Null results[] from DAO request"
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