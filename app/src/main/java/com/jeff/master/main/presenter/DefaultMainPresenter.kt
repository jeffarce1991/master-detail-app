package com.jeff.master.main.presenter

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.jeff.master.Constants
import com.jeff.master.database.local.Media
import com.jeff.master.database.usecase.local.loader.PhotoLocalLoader
import com.jeff.master.database.usecase.local.saver.PhotoLocalSaver
import com.jeff.master.webservices.exception.NoInternetException
import com.jeff.master.webservices.internet.RxInternet
import com.jeff.master.main.view.MainView
import com.jeff.master.supplychain.track.MediaLoader
import com.jeff.master.webservices.api.photos.PhotosApi
import com.jeff.master.webservices.api.RetrofitClientInstance
import com.jeff.master.utilities.rx.RxSchedulerUtils
import io.reactivex.*
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class DefaultMainPresenter @Inject
constructor(
    private val internet: RxInternet,
    private val localLoader: PhotoLocalLoader,
    private val localSaver: PhotoLocalSaver,
    private val schedulerUtils: RxSchedulerUtils,
    private val loader: MediaLoader
) : MvpBasePresenter<MainView>(),
    MainPresenter {

    lateinit var view: MainView

    lateinit var disposable: Disposable

    private fun getApi(): PhotosApi {

        /*Create handle for the RetrofitInstance interface*/
        return RetrofitClientInstance.getRxRetrofitInstance(
            Constants.Gateways.JSONPLACEHOLDER
        )!!.create(
            PhotosApi::class.java
        )
    }

    override fun getTracks() {
        internet.isConnected()
            .andThen(loader.loadAll())
            .compose(schedulerUtils.forSingle())
            .subscribe(object : SingleObserver<List<Media>>{
                override fun onSuccess(t: List<Media>) {
                    Timber.d("==q onSuccess $t" )
                    view.hideProgress()
                    if (t.isNotEmpty()) {
                        view.generateDataList(t)
                        view.showToast("Data loaded Remotely")
                    } else {
                        view.showLoadingDataFailed()
                    }
                    dispose()
                }

                override fun onSubscribe(d: Disposable) {
                    view.showProgressRemote()
                    disposable = d
                }

                override fun onError(e: Throwable) {
                    Timber.d("==q onError $e" )
                    e.printStackTrace()

                    view.hideProgress()

                    if (e is NoInternetException) {
                        //getPhotosFromLocal()
                    } else {
                        dispose()
                    }
                }
            })
    }


    /*fun getPhotosFromLocal(){
        loader.loadAllFromLocal()
            .compose(schedulerUtils.forSingle())
            .subscribe(object : SingleObserver<List<Photo>>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                    view.showProgressLocal()
                }

                override fun onSuccess(t: List<Photo>) {
                    Timber.d("==q loadAll onSuccess ${t.size}")

                    view.hideProgress()

                    if (t.isNotEmpty()) {
                        view.showToast("Data loaded Locally")
                        view.generateDataList(t)
                    } else {
                        view.showLoadingDataFailed()
                    }
                    dispose()
                }

                override fun onError(e: Throwable) {
                    Timber.d("==q Load Photos Failed $e")

                    view.hideProgress()
                    dispose()

                }
            })
    }*/

    override fun attachView(view: MainView) {
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