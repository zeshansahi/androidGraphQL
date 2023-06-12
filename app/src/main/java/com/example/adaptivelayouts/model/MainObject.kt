package com.example.adaptivelayouts.model


data class MainObject(

    var singleCountry: List<SingleCountry> = arrayListOf(),

    )


data class SingleCountry(
    var code: String? = null,
    var name: String? = null,
    var emoji: String? = null,
    var currency: String? = null,
    var languages: String? = null,
    var continent: String? = null,
)

data class DetailedCountry(
    var code: String? = null,
    var name: String? = null,
    var emoji: String? = null,
    var currency: String? = null,
    var languages: List<String>? = null,
    var continent: String? = null,
)







