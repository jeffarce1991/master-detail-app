package com.jeff.master.supplychain.media

import dagger.Binds
import dagger.Module

@Module
abstract class MediaUseCaseModule {

    @Binds
    abstract fun bindMediaLoader(defaultTrackLoader: DefaultMediaLoader): MediaLoader
}