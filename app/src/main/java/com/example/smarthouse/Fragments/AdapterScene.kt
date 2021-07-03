package com.example.smarthouse.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthouse.R
import com.example.smarthouse.common.Api
import com.example.smarthouse.common.Models.ModelDevice
import com.example.smarthouse.common.Models.ModelRoom
import com.example.smarthouse.common.Models.ModelScene
import com.example.smarthouse.common.initRetrofit
import com.example.smarthouse.common.showMessage
import com.google.android.material.switchmaterial.SwitchMaterial
import retrofit2.Call
import retrofit2.Response

class AdapterScene(val listDataScene: List<ModelScene>): RecyclerView.Adapter<AdapterScene.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name_scene)
        val type_connect = itemView.findViewById<ImageView>(R.id.action_connect)
        val device_connect = itemView.findViewById<ImageView>(R.id.device)
        val is_use = itemView.findViewById<SwitchMaterial>(R.id.is_use)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_scene, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = listDataScene[position].name
        holder.is_use.isChecked = listDataScene[position].is_use
        holder.type_connect.setImageResource(
            when(listDataScene[position].type_connect){
                "time action" -> R.drawable.time_action
                "tap action" -> R.drawable.tap_action
                else -> R.drawable.time_action
        })

        holder.device_connect.setImageResource(
            when(listDataScene[position].device_connect){
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
                else -> R.drawable.clear_air
            }
        )
    }

    override fun getItemCount(): Int = listDataScene.size
}