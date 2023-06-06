package com.example.profile

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("data")
    val data: ArrayList<ProfileData>,
    val page: Int,
    val per_page: Int,
//    val support: Support,
    val total: Int,
    val total_pages: Int
)