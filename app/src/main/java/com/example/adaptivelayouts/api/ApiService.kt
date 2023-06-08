package com.example.adaptivelayouts.api

import com.example.adaptivelayouts.model.MainObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    suspend fun getCharacterList(
        @Query(value = "q", encoded = true) query: String,
        @Query("format",encoded = true) format:String="json"
    ): Response<MainObject>
}
