package com.jeff.master.main.list.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat.getActionView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.jeff.master.BuildConfig
import com.jeff.master.R
import com.jeff.master.adapter.MediaListAdapter
import com.jeff.master.android.base.extension.invokeSimpleDialog
import com.jeff.master.android.base.extension.longToast
import com.jeff.master.android.base.extension.shortToast
import com.jeff.master.database.local.Media
import com.jeff.master.databinding.ActivityMasterListBinding
import com.jeff.master.main.list.presenter.MasterListPresenter
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class MasterListActivity : MvpActivity<MasterListView, MasterListPresenter>(),
    MasterListView {

    @Inject
    internal lateinit var masterListPresenter: MasterListPresenter

    lateinit var binding : ActivityMasterListBinding
    private lateinit var adapter: MediaListAdapter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_list)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_master_list)
        setUpToolbarTitle()
        setOnRefreshListener()
        masterListPresenter.loadMediaList()



    }

    private fun setUpToolbarTitle() {
        setSupportActionBar(binding.toolbar)

        //supportActionBar!!.title = getString(R.string.app_name)
        supportActionBar!!.title = "Master List"

    }

    private fun setOnRefreshListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            masterListPresenter.loadMediaList()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        initializeSearchView(menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about ->         //add the function to perform here
                invokeSimpleDialog(
                    getString(R.string.app_name) + " " + BuildConfig.VERSION_NAME,
                    getString(R.string.about_description)
                            + "\n\n\nPublished Date: 7/11/2020"
                            + "\nDeveloped by : Jeff Arce"
                )
            R.id.exit ->         //add the function to perform here
                finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initializeSearchView(menu: Menu?) {
        val searchItem: MenuItem = menu!!.findItem(R.id.action_search)
        searchView =
            getActionView(searchItem) as SearchView
        searchView.setOnCloseListener { true }

        val searchPlate =
            searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        searchPlate.hint = "Search by title, artist, kind, genre"
        searchPlate.setHintTextColor(resources.getColor(R.color.light_gray))
        searchPlate.setTextColor(resources.getColor(R.color.white))

        val searchPlateView: View = searchView.findViewById(androidx.appcompat.R.id.search_plate)
        searchPlateView.setBackgroundColor(
            ContextCompat.getColor(
                this,
                android.R.color.transparent
            )
        )
    }
    //Method to generate List of data using RecyclerView with custom com.project.retrofit.adapter*//*
    override fun generateDataList(mediaList: List<Media>) {
        val sortedMediaList = sortByName(mediaList)
        adapter = MediaListAdapter(this, sortedMediaList)
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
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override fun showProgress() {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun showToast(message: String) {
        longToast(message)
    }

    override fun showError(message: String) {
        invokeSimpleDialog("Error!",
            "OK",
            message
        )
    }

    override fun showEmptyListError() {
        invokeSimpleDialog("Error!",
            "Load Remotely",
            "Close",
            "No Data Saved Locally"
        ) { masterListPresenter.loadMediaListRemotely() }
    }

    override fun showNoInternetError() {
        invokeSimpleDialog("Error!",
            "Retry",
            "Close",
            "No Internet Connection"
        ) { masterListPresenter.loadMediaListRemotely() }
    }

}
