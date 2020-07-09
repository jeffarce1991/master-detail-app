package com.jeff.master.main.presenter

import dagger.Binds
import dagger.Module

@Module
abstract class MasterListPresenterModule {

    @Binds
    abstract fun bindMasterListPresenter(
        defaultMasterListPresenter: DefaultMasterListPresenter): MasterListPresenter
}