package com.abanoub.marvel.data.remote

import com.abanoub.marvel.data.model.ApiResponse
import com.abanoub.marvel.data.Client
import com.abanoub.marvel.data.ResponseWrapper
import com.abanoub.marvel.data.Services
import com.abanoub.marvel.data.model.Character

class GetCharacterRemote {

    fun getCharacters(errCallback: BaseCallBack, callback: OnCallback) {
        Client.getInstance().create(Services::class.java)
            .getCharacters(Client.TIME_STAMP, Client.API_KEY, Client.HASH_KEY)
            .enqueue(object : ResponseWrapper<ApiResponse>(errCallback) {
                override fun onSuccessCase(body: ApiResponse) {
                    callback.onCallback(body.data!!.results)
                }
            })
    }

    interface OnCallback {
        fun onCallback(body: List<Character>?)
    }
}