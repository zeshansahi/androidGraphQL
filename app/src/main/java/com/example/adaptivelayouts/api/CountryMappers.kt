package com.example.adaptivelayouts.api

import com.example.adaptivelayouts.CountriesQuery
import com.example.adaptivelayouts.CountryQuery
import com.example.adaptivelayouts.model.DetailedCountry
import com.example.adaptivelayouts.model.SingleCountry


fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        currency = currency ?: "No currency",
        languages = languages?.mapNotNull { it?.name },
        continent = continent?.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SingleCountry {
    return  SingleCountry(
        code = code,
        name = name,
        emoji = emoji,

    )
}