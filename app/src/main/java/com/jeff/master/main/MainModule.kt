package com.jeff.master.main

import com.jeff.master.ActivityScope
import com.jeff.master.main.presenter.MainPresenterModule
import com.jeff.master.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainPresenterModule::class])
    fun mainActivity(): MainActivity
}