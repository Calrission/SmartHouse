package com.example.smarthouse.MessageScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smarthouse.R
import com.example.smarthouse.common.Api
import com.example.smarthouse.common.ModelMessage
import com.example.smarthouse.common.initRetrofit
import com.example.smarthouse.common.showMessage
import kotlinx.android.synthetic.main.activity_message.*
import retrofit2.Call
import retrofit2.Response

class MessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        back_message.setOnClickListener {
            finish()
        }

        initRetrofit().create(Api::class.java).getMessage().enqueue(object: retrofit2.Callback<List<ModelMessage>>{
            override fun onResponse(
                call: Call<List<ModelMessage>>,
                response: Response<List<ModelMessage>>
            ) {
                if (response.isSuccessful){
                    rec_message.apply{
                        adapter = AdapterMessage(response.body()!!)
                        layoutManager = LinearLayoutManager(this@MessageActivity)
                    }
                }else
                    showMessage(this@MessageActivity, "Fail get list MESSAGE: Null body")
            }

            override fun onFailure(call: Call<List<ModelMessage>>, t: Throwable) {
                showMessage(this@MessageActivity, "Fail get list MESSAGE")
            }

        })
    }
}