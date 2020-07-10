package com.jeff.master.webservices.usecase

import com.jeff.master.webservices.usecase.loader.DefaultMediaRemoteLoader
import com.jeff.master.webservices.usecase.loader.MediaRemoteLoader
import dagger.Binds
import dagger.Module

@Module
interface WebServiceUseCaseModule {

    @Binds
    fun bindTrackRemoteLoader(
            defaultTrackRemoteLoader: DefaultMediaRemoteLoader):
            MediaRemoteLoader
}