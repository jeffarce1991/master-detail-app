package com.jeff.master.supplychain.track

import dagger.Binds
import dagger.Module

@Module
abstract class PhotoUseCaseModule {

    @Binds
    abstract fun bindPhotoLoader(defaultPhotoLoader: DefaultPhotoLoader): PhotoLoader

    @Binds
    abstract fun bindMediaLoader(defaultTrackLoader: DefaultMediaLoader): MediaLoader
}