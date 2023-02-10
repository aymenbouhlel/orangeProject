package com.orangeproject.utils


sealed class ResponseHTTP<T>(val data:T?=null, val message:String?=null) {

    class Success<T>(data:T?=null):ResponseHTTP<T>(data)
    class Error<T>(message:String?=null, data:T?=null):ResponseHTTP<T>(data, message)
    class Loading<T>(data:T?=null):ResponseHTTP<T>(data)
}