package com.abanoub.marvel.detailed

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.abanoub.marvel.base.BaseViewModel
import com.abanoub.marvel.data.model.*
import com.abanoub.marvel.data.remote.GetCharacterDataRemote
import java.util.ArrayList

class DetailedViewModel(application: Application) : BaseViewModel(application) {

    var comicsLiveData = MutableLiveData<ArrayList<CharacterData>>()
    var eventsLiveData = MutableLiveData<ArrayList<CharacterData>>()
    var seriesLiveData = MutableLiveData<ArrayList<CharacterData>>()
    var storiesLiveData = MutableLiveData<ArrayList<CharacterData>>()

    fun getCharacterData(characterId: Int, data: String) {
        GetCharacterDataRemote().getCharacterData(characterId, data, this,
            object : GetCharacterDataRemote.OnCallback {
                override fun onCallback(body: Results<CharacterData>?) {
                    if (data.equals("comics"))
                        comicsLiveData.setValue(body!!.results)
                    else if (data.equals("events"))
                        eventsLiveData.setValue(body!!.results)
                    else if (data.equals("series"))
                        seriesLiveData.setValue(body!!.results)
                    else if (data.equals("stories"))
                        storiesLiveData.setValue(body!!.results)
                }
            })
    }
}