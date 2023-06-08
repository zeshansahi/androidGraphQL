package com.example.adaptivelayouts.listFragment

import com.example.adaptivelayouts.api.ApiService
import javax.inject.Inject

class ListRepository @Inject constructor(val apiService: ApiService) {

    suspend fun getTempList(query: String) =
        apiService.getCharacterList(query)
}