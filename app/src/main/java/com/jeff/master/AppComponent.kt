package com.jeff.master

import android.app.Application
import com.jeff.master.database.DatabaseModule
import com.jeff.master.webservices.internet.RxInternetModule
import com.jeff.master.main.MainModule
import com.jeff.master.supplychain.media.MediaUseCaseModule
import com.jeff.master.utilities.UtilityModule
import com.jeff.master.webservices.api.ApiModule
import com.jeff.master.webservices.usecase.WebServiceUseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class,
    MainModule::class,
    AppModule::class,
    RxInternetModule::class,
    UtilityModule::class,
    DatabaseModule::class,
    ApiModule::class,
    WebServiceUseCaseModule::class,
    MediaUseCaseModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(myApplication: MyApplication)
}