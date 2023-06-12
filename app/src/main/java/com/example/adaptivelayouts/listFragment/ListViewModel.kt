package com.example.adaptivelayouts.listFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adaptivelayouts.api.Resource
import com.example.adaptivelayouts.api.toDetailedCountry
import com.example.adaptivelayouts.api.toSimpleCountry
import com.example.adaptivelayouts.model.DetailedCountry
import com.example.adaptivelayouts.model.MainObject
import com.example.adaptivelayouts.model.SingleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: ListRepository
) : ViewModel() {
    private val TAG = "PostViewModel"
    val countriesList: MutableLiveData<Resource<MainObject>> =
        MutableLiveData<Resource<MainObject>>()
    val detailedCountry: MutableLiveData<Resource<DetailedCountry>> =
        MutableLiveData<Resource<DetailedCountry>>()


    val sharedData: MutableLiveData<SingleCountry> = MutableLiveData()

    fun updateData(newData: SingleCountry) {
        sharedData.value = newData
    }


    init {
        getCountiesList()
    }

    private fun getCountiesList() = viewModelScope.launch {

        countriesList.postValue(Resource.loading("loading", null))
        try {
            repository.getCountriesList().let {
                if (it.data != null) {
                    var mainObject =
                        MainObject(singleCountry = it.data?.countries?.map { it?.toSimpleCountry()!! }!!)
                    countriesList.postValue(Resource.success(mainObject))
                } else {
                    countriesList.postValue(Resource.error("Error code: ${it.errors}", null))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            countriesList.postValue(Resource.error(e.message!!, null))
        }
    }


    private fun getSingleCountryDetail(countryId: String) = viewModelScope.launch {

        detailedCountry.postValue(Resource.loading("loading", null))
        try {
            repository.getCountry(countryId).let {
                if (it.data != null) {
                    var country = it.data?.country?.toDetailedCountry()
                    detailedCountry.postValue(Resource.success(country))
                } else {
                    detailedCountry.postValue(Resource.error("Error code: ${it.errors}", null))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            detailedCountry.postValue(Resource.error(e.message!!, null))
        }
    }
}