package com.example.smarthouse.SearchDeviceScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthouse.R
import com.example.smarthouse.common.Api
import com.example.smarthouse.common.ModelTag
import com.example.smarthouse.common.Models.ModelDevice
import com.example.smarthouse.common.Models.ModelRoom
import com.example.smarthouse.common.initRetrofit
import com.example.smarthouse.common.showMessage
import retrofit2.Call
import retrofit2.Response

class AdapterDeviceTag(val listDataDevice: List<ModelDevice>): RecyclerView.Adapter<AdapterDeviceTag.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name_device = itemView.findViewById<TextView>(R.id.name_device_tag)
        val img_device = itemView.findViewById<ImageView>(R.id.img_device_tag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rec_device_tag, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name_device.text = listDataDevice[position].name
        holder.img_device.setImageResource(
            when(listDataDevice[position].type){
                "camera" -> R.drawable.camera
                "cleaner" -> R.drawable.cleaner
                "cleaner air" -> R.drawable.clear_air
                "hair" -> R.drawable.hair
                "scooter" -> R.drawable.scooter
                "weigher" -> R.drawable.weighing
                "sensor" -> R.drawable.sensor
                "kettle" -> R.drawable.teapot
                "light" -> R.drawable.light
                "tv box" -> R.drawable.tv_box
                "power filter" -> R.drawable.network_filter
                "other" -> R.drawable.icon
                else -> R.drawable.clear_air
            }
        )
    }

    override fun getItemCount(): Int = listDataDevice.size
}