package com.example.smarthouse.MessageScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthouse.R
import com.example.smarthouse.common.Api
import com.example.smarthouse.common.ModelMessage
import com.example.smarthouse.common.Models.ModelDevice
import com.example.smarthouse.common.Models.ModelRoom
import com.example.smarthouse.common.initRetrofit
import com.example.smarthouse.common.showMessage
import retrofit2.Call
import retrofit2.Response

class AdapterMessage(val listDataMessage: List<ModelMessage>): RecyclerView.Adapter<AdapterMessage.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val geo = itemView.findViewById<TextView>(R.id.geo_message)
        val text = itemView.findViewById<TextView>(R.id.text_message)
        val name = itemView.findViewById<TextView>(R.id.name_device_message)
        val icon = itemView.findViewById<ImageView>(R.id.icon_device_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = listDataMessage[position].time + " | " + listDataMessage[position].text
        initRetrofit().create(Api::class.java).getDevice(listDataMessage[position].id_device).enqueue(object: retrofit2.Callback<ModelDevice>{
            override fun onResponse(
                call: Call<ModelDevice>,
                response: Response<ModelDevice>
            ) {
                if (response.isSuccessful){
                    val model = response.body()
                    holder.name.text = model!!.name
                    holder.icon.setImageResource(
                        when(model.type){
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
                }else
                    showMessage(holder.itemView.context, "Fail get DEVICE: Null body")
            }

            override fun onFailure(call: Call<ModelDevice>, t: Throwable) {
                showMessage(holder.itemView.context, "Fail get DEVICE")
            }

        })
    }

    override fun getItemCount(): Int = listDataMessage.size
}