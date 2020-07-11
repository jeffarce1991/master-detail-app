package com.jeff.master.main.list.presenter

import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.jeff.master.main.list.view.MasterListView

interface MasterListPresenter: MvpPresenter<MasterListView> {
    fun loadMediaList()
    fun loadMediaListRemotely()
}