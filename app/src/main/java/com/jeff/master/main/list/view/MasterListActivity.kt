package com.jeff.master.main.list.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.jeff.master.R
import com.jeff.master.adapter.CustomAdapter
import com.jeff.master.android.base.extension.invokeSimpleDialog
import com.jeff.master.android.base.extension.longToast
import com.jeff.master.database.local.Photo
import com.jeff.master.database.local.Media
import com.jeff.master.databinding.ActivityMasterListBinding
import com.jeff.master.main.detail.view.MasterDetailActivity
import com.jeff.master.main.list.presenter.MasterListPresenter
import dagger.android.AndroidInjection
import javax.inject.Inject


class MasterListActivity : MvpActivity<MasterListView, MasterListPresenter>(),
    MasterListView {
    private lateinit var adapter: CustomAdapter

    lateinit var binding : ActivityMasterListBinding

    lateinit var photos : List<Photo>


    @Inject
    internal lateinit var masterListPresenter: MasterListPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_list)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_master_list)

        masterListPresenter.loadMediaList()
    }


    private fun setUpToolbarTitle() {
        setSupportActionBar(binding.toolbar)

        supportActionBar!!.title = ""
    }

    //Method to generate List of data using RecyclerView with custom com.project.retrofit.adapter*//*
    override fun generateDataList(mediaList: List<Media>) {
        val sortedMediaList = sortByName(mediaList)
        adapter = CustomAdapter(this, sortedMediaList)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@MasterListActivity)
        binding.customRecyclerView.layoutManager = layoutManager
        binding.customRecyclerView.adapter = adapter
    }

    private fun sortByName(list: List<Media>): List<Media> {
        return list.sortedBy { it.trackName }
    }


    override fun createPresenter(): MasterListPresenter {
        return masterListPresenter
    }

    override fun hideProgress() {
        binding.progressBar.visibility = GONE
    }

    override fun showProgress() {
        binding.progressBar.visibility = VISIBLE
    }

    override fun showLoadingDataFailed() {
        longToast("Loading data failed")
        /*invokeSimpleDialog("",
            "OK",
            "List is empty or null.")*/
    }

    override fun showToast(message: String) {
        longToast(message)
    }

    override fun showError(message: String) {
        invokeSimpleDialog("Error!",
            "Retry",
            message
        ) { masterListPresenter.loadMediaList() }
    }

}
