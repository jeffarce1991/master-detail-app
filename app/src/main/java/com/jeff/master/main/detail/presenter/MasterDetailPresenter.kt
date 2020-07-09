package com.jeff.master.main.detail.presenter

import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.jeff.master.main.detail.view.MasterDetailView

interface MasterDetailPresenter : MvpPresenter<MasterDetailView>{
    fun loadDetail(id: String)
}
