package com.abanoub.marvel.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.abanoub.marvel.base.PaginationBaseViewModel
import com.abanoub.marvel.data.model.Character
import com.abanoub.marvel.data.model.Results
import com.abanoub.marvel.data.remote.GetCharacterRemote
import com.abanoub.marvel.data.remote.SearchCharacterRemote
import java.util.ArrayList

class MainViewModel(application: Application) : PaginationBaseViewModel(application) {

    var charactersLiveData = MutableLiveData<ArrayList<Character>>()
    var onSearchLiveData = MutableLiveData<ArrayList<Character>>()

    fun getCharacters(shouldClearList: Boolean) {
        paginationLoadingProgress.value=true
        GetCharacterRemote().getCharacters(
            if (shouldClearList) 0 else offset + 20,
            this,
            object : GetCharacterRemote.OnCallback {
                override fun onCallback(body: Results<Character>?) {
                    paginationLoadingProgress.value=false
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

    fun search(txt:String) {
        SearchCharacterRemote().searchCharacters(txt,
            this,
            object : SearchCharacterRemote.OnCallback {
                override fun onCallback(body: Results<Character>?) {
                    onSearchLiveData.setValue(body!!.results)
                }
            })
    }
}