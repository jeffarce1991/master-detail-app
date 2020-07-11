package com.jeff.master.main.list.presenter

import android.view.Menu
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
                        view.showEmptyListError()
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
                        view.showNoInternetError()
                    } else {
                        view.showError(e.message!!)
                    }
                    dispose()
                }
            })
    }

    override fun loadMediaListRemotely() {
        loader.loadAllRemotely()
        .compose(schedulerUtils.forSingle())
        .subscribe(object : SingleObserver<List<Media>>{
            override fun onSuccess(t: List<Media>) {
                Timber.d("==q onSuccess $t" )
                view.hideProgress()
                view.generateDataList(t)
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
                    view.showNoInternetError()
                } else {
                    view.showError(e.message!!)
                }
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