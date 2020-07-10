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
import com.jeff.master.adapter.CustomAdapter.CustomViewHolder
import com.jeff.master.database.local.Media
import com.jeff.master.databinding.CustomRowBinding
import com.jeff.master.main.detail.view.MasterDetailActivity
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.Comparator

internal class CustomAdapter(
    private val context: Context,
    private val dataList: List<Media>
) : RecyclerView.Adapter<CustomViewHolder>() {

    internal inner class CustomViewHolder(binding: CustomRowBinding) :
        ViewHolder(binding.root) {
        var item: ConstraintLayout = binding.item
        var txtTitle: TextView = binding.title
        var txtGenre: TextView = binding.genre
        var txtPrice: TextView = binding.price
        val coverImage: ImageView = binding.coverImage

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val binding = DataBindingUtil.inflate<CustomRowBinding>(LayoutInflater.from(p0.context),
            R.layout.media_item,
            p0,
            false)
        //sortAlphabetically()
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        setTrackName(holder, position)
        holder.txtGenre.text = dataList[position].genre
        holder.txtPrice.text = dataList[position].currency + dataList[position].price.toString()
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

    private fun setTrackName(holder: CustomViewHolder, position: Int) {
        val data = dataList[position].trackName
        if (data.length >= 24) {
            holder.txtTitle.text = data.substring(0, 24)+ "...";
        } else {
            holder.txtTitle.text = data;
        }
    }

    fun sortAlphabetically() {
        Collections.sort(dataList, Comparator<Media> { obj1, obj2 ->
            // ## Ascending order
            obj2.trackName
                .compareTo(obj1.trackName) // To compare string values

            // return Integer.valueOf(obj1.empId).compareTo(Integer.valueOf(obj2.empId)); // To compare integer values

            // ## Descending order
            // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
            // return Integer.valueOf(obj2.empId).compareTo(Integer.valueOf(obj1.empId)); // To compare integer values
        })
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}