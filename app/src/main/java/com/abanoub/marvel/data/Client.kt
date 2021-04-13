package com.abanoub.marvel.data

import com.abanoub.marvel.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {

    const val API_KEY =   "7005f73c19988d76c95c6e96a7e85462"
    const val HASH_KEY =   "539d97ebd971bc3172de8fb44a2b66cf"
    const val TIME_STAMP =   "1"
    const val CHARACTERS = "characters"

    const val LANG = "{lang}/"
    const val HOME = LANG + "home/"
    fun getInstance(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
