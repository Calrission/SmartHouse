package com.example.smarthouse.common

import com.example.smarthouse.common.Models.ModelDevice
import com.example.smarthouse.common.Models.ModelRoom
import com.example.smarthouse.common.Models.ModelScene
import com.example.smarthouse.common.Models.ModelUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface Api {
    @PATCH("SmartHouse.json")
    fun setUserData(@Body body: ModelUser = Info.modelUser): Call<ModelUser>

    @GET("SmartHouse.json")
    fun getUserData(): Call<ModelUser>

    @GET("SmartHouse/favorite_device.json")
    fun getFavorite(): Call<List<String>>

    @GET("SmartHouse/DeviceList.json")
    fun getDeviceList(): Call<List<ModelDevice>>

    @GET("SmartHouse/DeviceList/{id}.json")
    fun getDevice(@Path("id") id: String): Call<ModelDevice>

    @GET("SmartHouse/RoomList.json")
    fun getRoomList(): Call<List<ModelRoom>>

    @GET("SmartHouse/SceneList.json")
    fun getScene(): Call<List<ModelScene>>

    @GET("SmartHouse/Message.json")
    fun getMessage(): Call<List<ModelMessage>>

}