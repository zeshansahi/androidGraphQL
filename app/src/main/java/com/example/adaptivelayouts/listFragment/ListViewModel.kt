package com.example.adaptivelayouts.listFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adaptivelayouts.api.Resource
import com.example.adaptivelayouts.api.toSimpleCountry
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
    val weatherList: MutableLiveData<Resource<MainObject>> =
        MutableLiveData<Resource<MainObject>>()
    val sharedData: MutableLiveData<SingleCountry> = MutableLiveData()

    fun updateData(newData: SingleCountry) {
        sharedData.value = newData
    }


    init {
        getListAboutWeather()
    }

    fun getListAboutWeather() = viewModelScope.launch {

        weatherList.postValue(Resource.loading("loading", null))
        try {
            repository.getTempList().let {
                if (it.data !=null) {
                    var singleCountry: ArrayList<SingleCountry> = arrayListOf();
                    for (obj in it!!.data!!.countries!!){
                        singleCountry.add(obj?.toSimpleCountry()!!)
                    }
                    var mainObject=MainObject(SingleCountry=singleCountry)
                    weatherList.postValue(Resource.success(mainObject))
                } else {
                    weatherList.postValue(Resource.error("Error code: ${it.errors}", null))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            weatherList.postValue(Resource.error(e.message!!, null))
        }
    }
}