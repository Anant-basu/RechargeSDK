package com.example.recharge.retrofit

import ApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("session/create")
    fun createSession(@Body requestBody: Map<String, String>): Call<ApiResponse>
}