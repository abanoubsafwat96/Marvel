package com.abanoub.marvel.data.remote

import com.abanoub.marvel.data.model.ApiResponse
import com.abanoub.marvel.data.Client
import com.abanoub.marvel.data.ResponseWrapper
import com.abanoub.marvel.data.Services
import com.abanoub.marvel.data.model.CharacterData
import com.abanoub.marvel.data.model.Results

class GetCharacterDataRemote {

    fun getCharacterData(
        characterId: Int,
        data: String,
        errCallback: BaseCallBack,
        callback: OnCallback
    ) {
        Client.getInstance().create(Services::class.java)
            .getCharacterData(
                characterId,
                data,
                Client.TIME_STAMP,
                Client.API_KEY,
                Client.HASH_KEY)
            .enqueue(object : ResponseWrapper<ApiResponse<CharacterData>>(errCallback) {
                override fun onSuccessCase(body: ApiResponse<CharacterData>) {
                    callback.onCallback(body.data!!)
                }
            })
    }

    interface OnCallback {
        fun onCallback(body: Results<CharacterData>?)
    }
}