package com.kleberfh.conversas

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface Service {
    @Headers("X-API-Key: 82333f10")
    @GET("/mobiletest.json")
    fun getUsers(): Call<ArrayList<ItemChat>>

    companion object {
        fun newClient(): Service {
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://my.api.mockaroo.com/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(Service::class.java)
        }
    }
}