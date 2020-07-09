package com.jeff.master.main.detail.presenter

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.jeff.master.main.detail.view.MasterDetailView
import com.jeff.master.supplychain.track.MediaLoader
import com.jeff.master.utilities.rx.RxSchedulerUtils
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class DefaultMasterDetailPresenter @Inject
constructor(
    private val mediaLoader: MediaLoader,
    private val schedulers: RxSchedulerUtils
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

    override fun loadDetail(id: String) {
        TODO("Not yet implemented")
    }
}
