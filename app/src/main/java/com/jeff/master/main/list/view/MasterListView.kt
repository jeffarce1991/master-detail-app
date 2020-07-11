package com.jeff.master.main.list.view

import android.view.Menu
import com.hannesdorfmann.mosby.mvp.MvpView
import com.jeff.master.database.local.Media

interface MasterListView : MvpView {
     fun hideProgress()
     fun showProgress()

     fun showToast(message: String)
     fun showError(message: String)
     fun showEmptyListError()
     fun showNoInternetError()
     fun generateDataList(mediaList: List<Media>)
}