package com.abanoub.marvel.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.abanoub.marvel.base.PaginationBaseViewModel
import com.abanoub.marvel.data.model.Character
import com.abanoub.marvel.data.model.Characters
import com.abanoub.marvel.data.remote.GetCharacterRemote
import java.util.ArrayList

class MainViewModel(application: Application) : PaginationBaseViewModel(application) {

    var charactersLiveData = MutableLiveData<ArrayList<Character>>()

    fun getCharacters(shouldClearList: Boolean) {
        GetCharacterRemote().getCharacters(
            if (shouldClearList) 0 else offset + 20,
            this,
            object : GetCharacterRemote.OnCallback {
                override fun onCallback(body: Characters?) {

                    offset = if (shouldClearList) 0 else offset + 20
                    isaLastPage = body!!.total!! <= body!!.offset!! + 20
                    val value: ArrayList<Character>? = charactersLiveData.getValue()
                    if (value == null || shouldClearList) {
                        charactersLiveData.setValue(body!!.results)
                    } else {
                        value.addAll(body!!.results!!)
                        charactersLiveData.setValue(value)
                    }
                }
            })
    }
}