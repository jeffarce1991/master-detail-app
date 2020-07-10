package com.jeff.master.main.detail.presenter

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.jeff.master.database.local.Media
import com.jeff.master.main.detail.view.MasterDetailView
import com.jeff.master.supplychain.track.MediaLoader
import com.jeff.master.utilities.rx.RxSchedulerUtils
import com.jeff.master.webservices.exception.NoInternetException
import com.jeff.master.webservices.internet.RxInternet
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class DefaultMasterDetailPresenter @Inject
constructor(
    private val loader: MediaLoader,
    private val schedulers: RxSchedulerUtils,
    private val internet: RxInternet
): MvpBasePresenter<MasterDetailView>(),
    MasterDetailPresenter {


    lateinit var disposable: Disposable

    /*override fun loadCases(countryCode: String) {
        casesLoader.loadByCountryCode(countryCode)
            .compose(schedulers.forSingle())
            .subscribe(object : SingleObserver<Cases>{
                override fun onSuccess(t: Cases) {
                    Timber.d("==q countryCode $t")
                    view!!.setCases(t)
                    view!!.hideProgress()
                    dispose()
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                    view!!.showProgress()
                    Timber.d("==q onSubscribe countryCode")
                }

                override fun onError(e: Throwable) {
                    view!!.hideProgress()
                    Timber.e(e)
                    e.printStackTrace()
                    dispose()
                }
            })
    }*/

    private fun dispose() {
        if (!disposable.isDisposed) disposable.dispose()
    }

    override fun loadDetails(id: Int) {
        loader.loadByIdLocally(id)
            .compose(schedulers.forSingle())
            .subscribe(object : SingleObserver<Media> {
                override fun onSuccess(t: Media) {
                    Timber.d("==q onSuccess $t" )
                    view!!.hideProgress()
                    view!!.setDetails(t)
                    dispose()
                }

                override fun onSubscribe(d: Disposable) {
                    view!!.showProgress()
                    disposable = d
                }

                override fun onError(e: Throwable) {
                    Timber.d("==q onError $e" )
                    e.printStackTrace()

                    view!!.hideProgress()

                    if (e is NoInternetException) {
                        view!!.showError(e.message!!)
                    } else {
                        dispose()
                    }
                }
            })
    }
}
