package com.jeff.master.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jakewharton.picasso.OkHttp3Downloader
import com.jeff.master.R
import com.jeff.master.adapter.MediaListAdapter.CustomViewHolder
import com.jeff.master.database.local.Media
import com.jeff.master.databinding.MediaItemBinding
import com.jeff.master.main.detail.view.MasterDetailActivity
import com.squareup.picasso.Picasso
import java.util.*

internal class MediaListAdapter(
    private val context: Context,
    private var dataList: List<Media>
) : RecyclerView.Adapter<CustomViewHolder>() {

    internal inner class CustomViewHolder(binding: MediaItemBinding) :
        ViewHolder(binding.root) {
        var item: ConstraintLayout = binding.item
        var txtTitle: TextView = binding.title
        var txtGenre: TextView = binding.genre
        var txtPrice: TextView = binding.price
        val coverImage: ImageView = binding.coverImage

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val binding = DataBindingUtil.inflate<MediaItemBinding>(LayoutInflater.from(p0.context),
            R.layout.media_item,
            p0,
            false)
        //sortAlphabetically()
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        setTrackName(holder, position)
        val media = dataList[position]
        holder.txtGenre.text = media.genre
        holder.txtPrice.text =
            String.format(getCurrencySymbol(media.country, media.currency) + media.price)
        val builder = Picasso.Builder(context)
        builder.downloader(OkHttp3Downloader(context))
        builder.build().load(dataList[position].artWorkUrl)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .into(holder.coverImage)

        holder.item.setOnClickListener {
            val selectedMedia = dataList[position]
            val intent = MasterDetailActivity.getStartIntent(
                context,
                selectedMedia.id,
                selectedMedia.kind,
                selectedMedia.trackName
            )
            context.startActivity(intent)
        }
    }

    //This method will filter the list
    //here we are passing the filtered data
    //and assigning it to the list with notifydatasetchanged method
    fun update(countries: List<Media>) {
        dataList = countries
        notifyDataSetChanged()
    }


    private fun getCurrencySymbol(country: String, currency: String): String {
        return Currency.getInstance(currency)
            .getSymbol(Locale("en", country))
    }

    private fun setTrackName(holder: CustomViewHolder, position: Int) {
        val data = dataList[position].trackName
        if (data.length >= 32) {
            holder.txtTitle.text = String.format("${data.substring(0, 30)}...")
        } else {
            holder.txtTitle.text = data;
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}