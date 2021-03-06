package com.jeff.master.database

import android.app.Application
import androidx.room.Room
import com.jeff.master.R
import com.jeff.master.database.room.AppDatabase
import com.jeff.master.database.room.dao.MediaDao
import com.jeff.master.database.usecase.local.LocalUseCaseModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [LocalUseCaseModule::class])
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application,
            AppDatabase::class.java,
            application.getString(R.string.db_name))
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMediaDao(appDatabase: AppDatabase): MediaDao {
        return appDatabase.mediaDao()
    }
}