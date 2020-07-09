package com.jeff.master.main.detail.presenter

import dagger.Binds
import dagger.Module

@Module
abstract class MasterDetailPresenterModule {

    @Binds
    abstract fun bindMasterDetailPresenter(
        defaultMasterDetailPresenter: DefaultMasterDetailPresenter
    ): MasterDetailPresenter
}