package com.example.recharge.retrofit

import com.example.recharge.modal.Payload
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("category")
    fun getCategories(
        @Header("auth-token") authToken: String,
        @Header("ownerId") ownerId: String,
        @Header("ownerType") ownerType: String
    ): Call<Payload>

}