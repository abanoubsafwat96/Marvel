package com.abanoub.marvel.main

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import com.abanoub.marvel.base.PaginationBaseViewModel
import com.abanoub.marvel.data.model.Character
import com.abanoub.marvel.data.model.Results
import com.abanoub.marvel.data.remote.GetCharacterRemote
import com.abanoub.marvel.data.remote.SearchCharacterRemote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.ArrayList
import kotlin.coroutines.CoroutineContext

class MainViewModel(application: Application) : PaginationBaseViewModel(application) ,CoroutineScope{

    var charactersLiveData = MutableLiveData<ArrayList<Character>>()
    var onSearchLiveData = MutableLiveData<ArrayList<Character>>()

    override val coroutineContext: CoroutineContext = Dispatchers.Main
    val watcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        private var searchFor = ""
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val searchText = s.toString().trim()
            if (searchText == searchFor)
                return

            searchFor = searchText

            launch {
                delay(300)  //debounce timeOut
                if (searchText != searchFor) //disable search if user still typing for more than 300 ms
                    return@launch

                search(s.toString())
            }
        }
    }

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