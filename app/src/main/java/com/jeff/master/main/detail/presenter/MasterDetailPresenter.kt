package com.jeff.master.main.detail.presenter

import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.jeff.master.main.detail.view.CountryDetailView

interface CountryDetailPresenter : MvpPresenter<CountryDetailView>{
    fun loadDetail(countryCode: String)
}
