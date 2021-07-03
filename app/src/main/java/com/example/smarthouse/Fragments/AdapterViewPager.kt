package com.example.smarthouse.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthouse.R
import com.example.smarthouse.common.Models.ModelRoom

class AdapterViewPager(val list: List<ModelRoom>): RecyclerView.Adapter<AdapterViewPager.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rec = itemView.findViewById<RecyclerView>(R.id.rec)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_viewpager, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rec.apply{
            adapter = AdapterRecyclerView(list[position].list_device, list[position])
            layoutManager = GridLayoutManager(holder.itemView.context, 2)
        }
    }

    override fun getItemCount(): Int = list.size
}