package com.example.rasachatbotapp.network

import androidx.compose.runtime.snapshots.SnapshotStateList
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


private val retrofit = Retrofit.Builder()
    .baseUrl("https://rasa-nafia-salma.cloud.okteto.net")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val rasaApiService: RasaApiService = retrofit.create(RasaApiService::class.java)

interface RasaApiService {
    @POST("webhooks/rest/webhook")
    suspend fun sendMessage(
        @Body message: bodyRequest
    ): Response<SnapshotStateList<Message>>
}