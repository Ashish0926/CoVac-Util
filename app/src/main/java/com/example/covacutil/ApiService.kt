package com.example.covacutil

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://cdn-api.co-vin.in/api/v2/"

interface DataInterface{

    @GET("appointment/sessions/public/findByPin?")
    fun getSessions(@Query("pincode") pincode: String, @Query("date") date: String): Call<Data>
}

object ApiService{
    val dataInstance: DataInterface
    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        dataInstance = retrofit.create(DataInterface::class.java)
    }
}