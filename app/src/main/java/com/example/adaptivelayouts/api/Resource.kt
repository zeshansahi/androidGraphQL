package com.example.adaptivelayouts.api

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message:String?){
    companion object{

        fun <T> success(data:T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> loading(msg:String, data:T?): Resource<T> {
            return Resource(Status.LOADING, data, msg)
        }
        fun <T> error(msg:String, data:T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}