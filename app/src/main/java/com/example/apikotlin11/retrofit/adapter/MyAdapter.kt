package com.example.apikotlin11.retrofit.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apikotlin11.R
import com.example.apikotlin11.retrofit.ApiActivity
import com.example.apikotlin11.retrofit.ArticlesItem

class MyAdapter(val apiActivity: ApiActivity, val list: List<ArticlesItem?>) :
    RecyclerView.Adapter<MyAdapter.ViewData>() {


    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var img_news = itemView.findViewById<ImageView>(R.id.img_news)
        var txt_headlines = itemView.findViewById<TextView>(R.id.txt_headlines)
        var txt_subtitle = itemView.findViewById<TextView>(R.id.txt_subtitle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view = LayoutInflater.from(apiActivity).inflate(R.layout.item,parent,false)
        return  ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.txt_headlines.text = list[position]?.title
        holder.txt_subtitle.text = list[position]?.description
    }

    override fun getItemCount(): Int {
        return list.size
    }
}