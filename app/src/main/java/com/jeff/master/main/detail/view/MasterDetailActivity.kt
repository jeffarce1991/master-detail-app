package com.jeff.master.main.detail.view

import android.app.ProgressDialog
import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.jeff.covidtracker.R
import com.jeff.covidtracker.database.local.Cases
import com.jeff.covidtracker.databinding.ActivityCountryDetailBinding
import com.jeff.master.main.detail.presenter.DefaultCountryDetailPresenter
import com.jeff.covidtracker.utilities.extensions.toDisplay
import com.jeff.master.R
import com.jeff.master.databinding.ActivityCountryDetailBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class CountryDetailActivity : MvpActivity<CountryDetailView, DefaultCountryDetailPresenter>(),
    CountryDetailView {

    @Inject
    internal lateinit var countryDetailPresenter: DefaultCountryDetailPresenter

    private lateinit var progressDialog: ProgressDialog

    private lateinit var binding : ActivityCountryDetailBinding

    companion object {
        private var EXTRA_COUNTRY_NAME = "EXTRA_COUNTRY_NAME"
        private var EXTRA_COUNTRY_CODE = "EXTRA_COUNTRY_CODE"
        private var EXTRA_COUNTRY_ISO2 = "EXTRA_COUNTRY_ISO2"

        fun getStartIntent(
            context: Context,
            country : String,
            countryCode : String,
            iso2 : String
        ): Intent {
            return Intent(context, CountryDetailActivity::class.java)
                .putExtra(EXTRA_COUNTRY_NAME, country)
                .putExtra(EXTRA_COUNTRY_CODE, countryCode)
                .putExtra(EXTRA_COUNTRY_ISO2, iso2)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_country_detail)

        setUpToolbarTitle()
        countryDetailPresenter.loadCases(intent.getStringExtra(EXTRA_COUNTRY_CODE))


    }

    private fun setUpToolbarTitle() {
        setSupportActionBar(binding.countryDetailToolbar)

        supportActionBar!!.title = intent.getStringExtra(EXTRA_COUNTRY_NAME).capitalize()
        binding.countryDetailToolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun createPresenter(): DefaultCountryDetailPresenter {
        return countryDetailPresenter
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

    override fun showProgress() {
        progressDialog = show(
            this,
            getString(R.string.app_name),
            "Loading Cases...")
    }

}
