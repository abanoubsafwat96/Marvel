package com.abanoub.marvel.data

import com.abanoub.marvel.data.model.ApiResponse
import com.abanoub.marvel.data.model.Characters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET(Client.CHARACTERS)
    fun getCharacters(
        @Query("ts") timeStamp: String,
        @Query("apikey") api_key: String,
        @Query("hash") hash_key: String,
        @Query("offset") offset: Int
    ): Call<ApiResponse>
}