package com.example.adaptivelayouts.listFragment

import com.apollographql.apollo3.ApolloClient
import com.example.adaptivelayouts.CountriesQuery
import com.example.adaptivelayouts.CountryQuery
import com.example.adaptivelayouts.api.ApiService
import javax.inject.Inject

class ListRepository @Inject constructor(val apolloClient: ApolloClient) {

    //    suspend fun getTempList(query: String) =
//        apiService.getCharacterList(query)
    suspend fun getTempList() =
        apolloClient.query(CountriesQuery()).execute()
   suspend fun getCountry(id:String) =
        apolloClient.query(CountryQuery(id)).execute()
}