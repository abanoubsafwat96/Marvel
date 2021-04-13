package com.abanoub.marvel.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.abanoub.marvel.base.PaginationBaseViewModel
import com.abanoub.marvel.data.remote.GetCharacterRemote
import com.abanoub.marvel.data.model.Character

class MainViewModel(application: Application) : PaginationBaseViewModel(application) {

    var charactersLiveData = MutableLiveData<List<Character>>()

    fun getCharacters(shouldClearList: Boolean) {
        loadingProgress.value = true
        GetCharacterRemote().getCharacters(this, object : GetCharacterRemote.OnCallback {
            override fun onCallback(body: List<Character>?) {
                loadingProgress.value = false
                charactersLiveData.value = body
            }
        })
    }
}