package com.example.smarthouse.common.Models

data class ModelScene(
    val id: String,
    val name: String,
    val is_use: Boolean,
    val type_connect: String,
    val device_connect: String,
    val id_device: String
)
