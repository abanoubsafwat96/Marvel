package com.abanoub.marvel.detailed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abanoub.marvel.R
import com.abanoub.marvel.base.BaseActivity
import com.abanoub.marvel.data.model.Character
import com.abanoub.marvel.data.model.CharacterData
import com.abanoub.marvel.main.MainViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detailed.*
import kotlinx.android.synthetic.main.character_single_item.view.*

class DetailedActivity : BaseActivity<DetailedViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        var character = intent.getParcelableExtra<Character>("character")
        initViews(character)
    }

    private fun initViews(character: Character?) {
        var imageLink = character!!.getThumbnails()!!.getPath() + "." + character.getThumbnails()!!.getExtension()
        Glide.with(this)
            .load(imageLink)
            .placeholder(R.drawable.image_placeholder)
            .into(imageView)

        name.setText(character.getName())
        description.setText(character.getDescription())

        backBtn.setOnClickListener{
            onBackPressed()
        }

        if (isConnected()){
            getComics(character.getId()!!)
            getEvents(character.getId()!!)
            getSeries(character.getId()!!)
            getStories(character.getId()!!)
        }
    }

    private fun getStories(characterId: Int) {
        getViewModel().storiesLiveData.observe(this, Observer {characterData ->
            if (characterData==null) return@Observer

            stories_recyclerView.adapter=CharacterDataAdapter(characterData)
        })
        getViewModel().getCharacterData(characterId ,"stories")
    }

    private fun getSeries(characterId: Int) {
        getViewModel().seriesLiveData.observe(this, Observer {characterData ->
            if (characterData==null) return@Observer

            series_recyclerView.adapter=CharacterDataAdapter(characterData)
        })
        getViewModel().getCharacterData(characterId ,"series")
    }

    private fun getEvents(characterId: Int) {
        getViewModel().eventsLiveData.observe(this, Observer {characterData ->
            if (characterData==null) return@Observer

            events_recyclerView.adapter=CharacterDataAdapter(characterData)
        })
        getViewModel().getCharacterData(characterId ,"events")
    }

    private fun getComics(characterId: Int) {
        getViewModel().comicsLiveData.observe(this, Observer {characterData ->
            if (characterData==null) return@Observer

            comics_recyclerView.adapter=CharacterDataAdapter(characterData)
        })
        getViewModel().getCharacterData(characterId ,"comics")
    }

    override fun getViewModel(): DetailedViewModel {
        return ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
        ).get(DetailedViewModel::class.java)
    }
}