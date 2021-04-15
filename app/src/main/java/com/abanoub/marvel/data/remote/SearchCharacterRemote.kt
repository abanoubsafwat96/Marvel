package com.abanoub.marvel.data.remote

import com.abanoub.marvel.data.model.ApiResponse
import com.abanoub.marvel.data.Client
import com.abanoub.marvel.data.ResponseWrapper
import com.abanoub.marvel.data.Services
import com.abanoub.marvel.data.model.Character
import com.abanoub.marvel.data.model.Results

class SearchCharacterRemote {

    fun searchCharacters(searchName:String, errCallback: BaseCallBack, callback: OnCallback) {
        Client.getInstance().create(Services::class.java)
            .searchCharacters(searchName, Client.TIME_STAMP, Client.API_KEY, Client.HASH_KEY)
            .enqueue(object : ResponseWrapper<ApiResponse<Character>>(errCallback) {
                override fun onSuccessCase(body: ApiResponse<Character>) {
                    callback.onCallback(body.data!!)
                }
            })
    }

    interface OnCallback {
        fun onCallback(body: Results<Character>?)
    }
}