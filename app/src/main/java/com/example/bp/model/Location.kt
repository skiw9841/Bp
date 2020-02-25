package com.example.bp.com.example.bp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Location (
    @SerializedName("title")
    val title: String,
    @SerializedName("woeid")
    val woeid: Long
): Serializable