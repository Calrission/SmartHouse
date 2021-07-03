package com.example.smarthouse.SearchDeviceScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthouse.R
import com.example.smarthouse.common.ModelTag

class AdapterTag(val listDataTag: List<ModelTag>): RecyclerView.Adapter<AdapterTag.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name_tag = itemView.findViewById<TextView>(R.id.name_tag)
        val rec_device_tag = itemView.findViewById<RecyclerView>(R.id.rec_device_tag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rec_tag, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name_tag.text = listDataTag[position].name
        holder.rec_device_tag.apply {
            adapter = AdapterDeviceTag(listDataTag[position].list_device)
            layoutManager = GridLayoutManager(holder.itemView.context, 3)
        }
    }

    override fun getItemCount(): Int = listDataTag.size
}