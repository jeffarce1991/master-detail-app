package com.jeff.master.main.presenter

import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.jeff.master.main.view.MasterListView

interface MasterListPresenter: MvpPresenter<MasterListView> {
    fun getTracks()
}