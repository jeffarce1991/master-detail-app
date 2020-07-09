package com.jeff.master.main.detail.presenter

import dagger.Binds
import dagger.Module

@Module
abstract class CountryDetailPresenterModule {

    @Binds
    abstract fun bindCountryDetailPresenter(
        defaultCountryDetailPresenter: DefaultMasterDetailPresenter
    ): MasterDetailPresenter
}