package com.jeff.master.main

import com.jeff.master.ActivityScope
import com.jeff.master.main.detail.presenter.MasterDetailPresenterModule
import com.jeff.master.main.detail.view.MasterDetailActivity
import com.jeff.master.main.list.presenter.MasterListPresenterModule
import com.jeff.master.main.list.view.MasterListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MasterListPresenterModule::class])
    fun mainActivity(): MasterListActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MasterDetailPresenterModule::class])
    fun masterDetailActivity(): MasterDetailActivity
}