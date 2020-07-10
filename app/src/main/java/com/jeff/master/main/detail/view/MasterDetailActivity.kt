package com.jeff.master.main.detail.view


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.jakewharton.picasso.OkHttp3Downloader
import com.jeff.master.R
import com.jeff.master.android.base.extension.invokeSimpleDialog
import com.jeff.master.android.base.extension.shortToast
import com.jeff.master.database.local.Media
import com.jeff.master.databinding.ActivityMasterDetailBinding
import com.jeff.master.main.detail.presenter.DefaultMasterDetailPresenter
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject

class MasterDetailActivity : MvpActivity<MasterDetailView, DefaultMasterDetailPresenter>(),
    MasterDetailView {

    @Inject
    internal lateinit var masterDetailPresenter: DefaultMasterDetailPresenter

    private lateinit var binding : ActivityMasterDetailBinding

    companion object {
        private var EXTRA_ID = "EXTRA_ID"
        private var EXTRA_KIND = "EXTRA_KIND"
        private var EXTRA_TITLE = "EXTRA_TITLE"

        fun getStartIntent(
            context: Context,
            id : Int,
            kind : String,
            title : String


        ): Intent {
            return Intent(context, MasterDetailActivity::class.java)
                .putExtra(EXTRA_ID, id)
                .putExtra(EXTRA_KIND, kind)
                .putExtra(EXTRA_TITLE, title)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_detail)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_master_detail)

        setUpToolbarTitle()
        masterDetailPresenter.loadDetails(getId())


    }

    fun getId(): Int = intent.getIntExtra(EXTRA_ID,-1)

    private fun setUpToolbarTitle() {
        setSupportActionBar(binding.toolbar)

        val extras = intent.extras
        //supportActionBar!!.title = extras!!.getString(EXTRA_TITLE)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
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
        binding.progressBar.visibility = View.GONE
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun showError(message: String) {
        invokeSimpleDialog("Error!",
            "Retry",
            message
        ) { masterDetailPresenter.loadDetails(getId()) }
    }

    override fun setDetails(media: Media) {
        binding.description.text = media.longDescription
        setTitle(media.trackName)
        binding.artist.text = media.artistName
        binding.genre.text = media.genre
        binding.price.text =
            String.format(getCurrencySymbol(media.country, media.currency) + media.price)

        setImage(this, media.artWorkUrl, binding.coverImage)

        binding.title.setOnClickListener { shortToast(media.trackName) }
    }

    private fun setImage(context: Context, url: String, imageView: ImageView) {

        val builder = Picasso.Builder(context)
        builder.downloader(OkHttp3Downloader(context))
        builder.build().load(url)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .into(imageView)

    }

    private fun getCurrencySymbol(country: String, currency: String): String {
        return Currency.getInstance(currency)
            .getSymbol(Locale("en", country))
    }


    private fun setTitle(title: String) {
        if (title.length >= 24) {
            binding.title.text = String.format("${title.substring(0, 22).trim()}...")
        } else {
            binding.title.text = title;
        }
    }

}
