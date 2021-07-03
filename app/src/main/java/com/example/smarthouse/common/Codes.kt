package com.example.smarthouse.common

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Patterns
import com.example.smarthouse.common.Models.ModelUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun showMessage(context: Context, title: String){
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage("Попробуйте ещё раз")
        .setNegativeButton("OK", DialogInterface.OnClickListener { dialog, which ->
        })
        .show()
}

fun initRetrofit(): Retrofit{
    return Retrofit.Builder()
        .baseUrl("https://tasksworldskills-default-rtdb.firebaseio.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun auth(context: Context, modelUser: ModelUser, onResult: OnResult){
    initRetrofit().create(Api::class.java).getUserData().enqueue(object: Callback<ModelUser>{
        override fun onResponse(call: Call<ModelUser>, response: Response<ModelUser>) {
            val model = response.body()!!
            if (modelUser.password == model.password && modelUser.email == model.email){
                onResult.onResult()
                Info.modelUser = model
                context.getSharedPreferences("0", 0).edit()
                    .putBoolean("isFirst", false)
                    .apply()
            }else{
                showMessage(context, "Пользователь не найден")
            }
        }

        override fun onFailure(call: Call<ModelUser>, t: Throwable) {
            showMessage(context, "Ошибка Авторизации")
        }
    })
}

fun reg(context: Context, modelUser: ModelUser, onResult: OnResult){
    initRetrofit().create(Api::class.java).setUserData(modelUser).enqueue(object: Callback<ModelUser>{
        override fun onResponse(call: Call<ModelUser>, response: Response<ModelUser>) {
            auth(context, modelUser, onResult)
        }

        override fun onFailure(call: Call<ModelUser>, t: Throwable) {
            showMessage(context, "Ошибка Регистрации")
        }
    })
}

fun checkEmail(email: String): Boolean{
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}