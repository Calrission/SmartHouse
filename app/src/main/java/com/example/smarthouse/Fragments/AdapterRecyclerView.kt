package com.example.smarthouse.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthouse.R
import com.example.smarthouse.common.Api
import com.example.smarthouse.common.Models.ModelDevice
import com.example.smarthouse.common.Models.ModelRoom
import com.example.smarthouse.common.initRetrofit
import com.example.smarthouse.common.showMessage
import retrofit2.Call
import retrofit2.Response

class AdapterRecyclerView(val listDataDevice: List<String>, val modelRoom: ModelRoom): RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.img)
        val action = itemView.findViewById<ImageView>(R.id.action_button)
        val name = itemView.findViewById<TextView>(R.id.name)
        val geo = itemView.findViewById<TextView>(R.id.geo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_device, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        initRetrofit().create(Api::class.java).getDevice(listDataDevice[position]).enqueue(object: retrofit2.Callback<ModelDevice>{
            override fun onResponse(call: Call<ModelDevice>, response: Response<ModelDevice>) {
                if (response.isSuccessful){
                    val modelDevice = response.body()!!
                    holder.name.text = modelDevice.name
                    holder.geo.text = modelRoom.name
                    holder.img.setImageResource(
                        when(modelDevice.type){
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
                    if (modelDevice.type == "cleaner")
                        holder.action.visibility = View.VISIBLE
                }else{
                    showMessage(holder.itemView.context, "Fail get DEVICE: Null Body")
                }
            }

            override fun onFailure(call: Call<ModelDevice>, t: Throwable) {
                showMessage(holder.itemView.context, "Fail get DEVICE")
            }

        })
    }

    override fun getItemCount(): Int = listDataDevice.size
}