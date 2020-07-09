package com.jeff.master.main.presenter

import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.jeff.master.main.view.MainView

interface MainPresenter: MvpPresenter<MainView> {
    fun getTracks()
}