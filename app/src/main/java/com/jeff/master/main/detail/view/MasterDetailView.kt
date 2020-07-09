package com.jeff.master.main.detail.view

import com.hannesdorfmann.mosby.mvp.MvpView
import com.jeff.master.database.local.Media

interface CountryDetailView: MvpView {

    fun setDetails(media: Media)
    fun showProgress()
    fun hideProgress()
}