package com.example.smarthouse.Fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthouse.R
import com.example.smarthouse.common.OnCLickMenu

class AdapterMenuTags(val listDataRoom: List<String>, val onClickItemView: OnCLickMenu): RecyclerView.Adapter<AdapterMenuTags.ViewHolder>() {
    var view_now: TextView? = null
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name_tag = itemView.findViewById<TextView>(R.id.tag_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_menu_tags, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0){
            switch(holder.name_tag, holder.itemView.context)
        }
        holder.name_tag.text = listDataRoom[position]
        holder.itemView.setOnClickListener {
            onClickItemView.onClickRoom(position)
            switch(holder.name_tag, holder.itemView.context)
        }
    }

    override fun getItemCount(): Int = listDataRoom.size

    private fun switch(view: TextView, context: Context){
        if (view_now != null){
            view_now!!.setTextColor(context.getColor(R.color.colorHint))
            view_now!!.setBackgroundColor(context.getColor(R.color.colorBackItem2))
        }
        view.setTextColor(context.getColor(R.color.colorText))
        view.setBackgroundColor(context.getColor(R.color.colorAccent))
        view_now = view
    }
}