package com.jeff.master.webservices.usecase

import com.jeff.master.webservices.usecase.loader.DefaultPhotoRemoteLoader
import com.jeff.master.webservices.usecase.loader.DefaultMediaRemoteLoader
import com.jeff.master.webservices.usecase.loader.PhotoRemoteLoader
import com.jeff.master.webservices.usecase.loader.MediaRemoteLoader
import dagger.Binds
import dagger.Module

@Module
interface WebServiceUseCaseModule {

    @Binds
    fun bindPhotoRemoteLoader(
            defaultPhotoRemoteLoader: DefaultPhotoRemoteLoader):
            PhotoRemoteLoader
    @Binds
    fun bindTrackRemoteLoader(
            defaultTrackRemoteLoader: DefaultMediaRemoteLoader):
            MediaRemoteLoader
}