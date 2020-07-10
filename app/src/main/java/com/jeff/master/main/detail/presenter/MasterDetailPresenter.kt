package com.jeff.master.main.detail.presenter

import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.jeff.master.main.detail.view.MasterDetailView

interface MasterDetailPresenter : MvpPresenter<MasterDetailView>{
    fun loadDetails(id: Int)
}
