package com.example.profile.retrofit

import com.example.profile.UserData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("users")
    fun getData():Call<UserData>
}