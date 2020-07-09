package com.jeff.master.main.detail.view

import android.app.ProgressDialog
import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.jeff.master.main.detail.presenter.DefaultMasterDetailPresenter
import com.jeff.master.R
import com.jeff.master.database.local.Media
import com.jeff.master.databinding.ActivityCountryDetailBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class MasterDetailActivity : MvpActivity<MasterDetailView, DefaultMasterDetailPresenter>(),
    MasterDetailView {

    @Inject
    internal lateinit var masterDetailPresenter: DefaultMasterDetailPresenter

    private lateinit var progressDialog: ProgressDialog

    private lateinit var binding : ActivityCountryDetailBinding

    companion object {
        private var EXTRA_ID = "EXTRA_ID"
        private var EXTRA_TITLE = "EXTRA_TITLE"

        fun getStartIntent(
            context: Context,
            id : Int,
            title : String


        ): Intent {
            return Intent(context, MasterDetailActivity::class.java)
                .putExtra(EXTRA_ID, id)
                .putExtra(EXTRA_TITLE, title)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_detail)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_master_detail)

        setUpToolbarTitle()
        //countryDetailPresenter.loadCases(intent.getStringExtra(EXTRA_COUNTRY_CODE))


    }

    private fun setUpToolbarTitle() {
        setSupportActionBar(binding.countryDetailToolbar)

        val extras = intent.extras
        supportActionBar!!.title = extras!!.getString(EXTRA_TITLE)
        binding.countryDetailToolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun createPresenter(): DefaultMasterDetailPresenter {
        return masterDetailPresenter
    }

    /*override fun setCases(cases: Cases) {
        binding.countryDate.text = String.format("As of ${cases.date.toDisplay("MMM dd, yyyy")}")
        binding.countryConfirmedTotal.text = cases.totalCases!!.totalConfirmed.toString()
        binding.countryConfirmedToday.text = get(cases.newCases!!.newConfirmed)

        binding.countryDeathsTotal.text = cases.totalCases!!.totalDeaths.toString()
        binding.countryDeathsToday.text = get(cases.newCases!!.newDeaths)

        binding.countryRecoveredTotal.text = cases.totalCases!!.totalRecovered.toString()
        binding.countryRecoveredToday.text = get(cases.newCases!!.newRecovered)
    }*/

    fun get(x: Int?, y: Int?): String{
        return String.format("+${(x!! - y!!)}")
    }

    fun get(x: Int?): String{
        return String.format("+${x!!}")
    }

    override fun hideProgress() {
        progressDialog.dismiss()
    }

    override fun setDetails(media: Media) {
        TODO("Not yet implemented")
    }

    override fun showProgress() {
        progressDialog = show(
            this,
            getString(R.string.app_name),
            "Loading Cases...")
    }

}
