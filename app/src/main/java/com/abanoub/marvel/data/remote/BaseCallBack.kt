package com.abanoub.marvel.data.remote

interface BaseCallBack {
    fun onDataNotAvailable(errorMsg: String?, errorCode: String?)
}