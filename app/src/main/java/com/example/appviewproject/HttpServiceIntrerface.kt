package com.example.appviewproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface HttpServiceIntrerface {
    @GET("/getIntroImageUrl")
    fun getIntroImageUrl(): Call<IntroImage>
}