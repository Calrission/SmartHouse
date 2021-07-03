package com.example.smarthouse.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthouse.R
import com.example.smarthouse.common.Models.ModelRoom
import com.example.smarthouse.common.OnCLickMenu

class AdapterMenuRoom(val listDataRoom: List<ModelRoom>, val onClickItemView: OnCLickMenu): RecyclerView.Adapter<AdapterMenuRoom.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name_room)
        val count_device = itemView.findViewById<TextView>(R.id.count_device_room)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_room_menu, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = listDataRoom[position].name
        holder.count_device.text = listDataRoom[position].list_device.size.toString()
        holder.itemView.setOnClickListener {
            onClickItemView.onClickRoom(position)
        }
    }

    override fun getItemCount(): Int = listDataRoom.size
}