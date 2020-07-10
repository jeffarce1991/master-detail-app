package com.jeff.master.database.usecase.local

import com.jeff.master.database.usecase.local.loader.DefaultMediaLocalLoader
import com.jeff.master.database.usecase.local.loader.MediaLocalLoader
import com.jeff.master.database.usecase.local.saver.DefaultMediaLocalSaver
import com.jeff.master.database.usecase.local.saver.MediaLocalSaver
import dagger.Binds
import dagger.Module

@Module
interface LocalUseCaseModule {

    @Binds
    fun bindMediaLocalLoader(implementation: DefaultMediaLocalLoader): MediaLocalLoader

    @Binds
    fun bindMediaLocalSaver(implementation: DefaultMediaLocalSaver): MediaLocalSaver
}