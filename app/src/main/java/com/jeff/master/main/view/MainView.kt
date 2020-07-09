package com.jeff.master.main.view

import com.hannesdorfmann.mosby.mvp.MvpView
import com.jeff.master.database.local.Media

interface MainView : MvpView {
     fun hideProgress()
     fun showProgressRemote()
     fun showProgressLocal()

     fun showLoadingDataFailed()
     fun showToast(message: String)
     fun generateDataList(track: List<Media>)
}