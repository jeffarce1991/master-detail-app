package com.jeff.master.main.list.presenter

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.jeff.master.database.local.Media
import com.jeff.master.webservices.exception.NoInternetException
import com.jeff.master.webservices.internet.RxInternet
import com.jeff.master.main.list.view.MasterListView
import com.jeff.master.supplychain.media.MediaLoader
import com.jeff.master.utilities.exception.EmptyResultException
import com.jeff.master.utilities.rx.RxSchedulerUtils
import io.reactivex.*
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class DefaultMasterListPresenter @Inject
constructor(
    private val internet: RxInternet,
    private val schedulerUtils: RxSchedulerUtils,
    private val loader: MediaLoader
) : MvpBasePresenter<MasterListView>(),
    MasterListPresenter {

    lateinit var view: MasterListView

    lateinit var disposable: Disposable

    override fun loadMediaList() {
        loader.loadAll()
            .compose(schedulerUtils.forSingle())
            .subscribe(object : SingleObserver<List<Media>>{
                override fun onSuccess(t: List<Media>) {
                    Timber.d("==q onSuccess $t" )
                    view.hideProgress()
                    if (t.isNotEmpty()) {
                        view.generateDataList(t)
                    } else {
                        view.showError("Media List is Empty!")
                    }
                    dispose()
                }

                override fun onSubscribe(d: Disposable) {
                    view.showProgress()
                    disposable = d
                }

                override fun onError(e: Throwable) {
                    Timber.d("==q onError $e" )
                    e.printStackTrace()

                    view.hideProgress()

                    if (e is NoInternetException) {
                        //loadMediaListLocally()
                        view.showError(e.message!!)
                        dispose()
                    }
                    if (e is EmptyResultException){
                        view.showError(e.message!!)
                        dispose()
                    }
                }
            })
    }


    fun loadMediaListLocally(){
        loader.loadAllLocally()
            .compose(schedulerUtils.forSingle())
            .subscribe(object : SingleObserver<List<Media>>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                    view.showProgress()
                }

                override fun onSuccess(t: List<Media>) {
                    Timber.d("==q loadAll onSuccess ${t.size}")

                    view.hideProgress()

                    if (t.isNotEmpty()) {
                        view.showToast("Data loaded Locally")
                        view.generateDataList(t)
                    } else {
                        view.showError("Media List is Empty!")
                    }
                    dispose()
                }

                override fun onError(e: Throwable) {
                    Timber.d("==q Load Photos Failed $e")

                    view.hideProgress()
                    dispose()

                }
            })
    }

    override fun attachView(view: MasterListView) {
        super.attachView(view)
        this.view = view
    }

    private fun dispose() {
        if (!disposable.isDisposed) disposable.dispose()
    }

    override fun detachView(retainInstance: Boolean) {
        dispose()
    }

}