package com.abanoub.marvel.detailed

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abanoub.marvel.R
import com.abanoub.marvel.base.BaseActivity
import com.abanoub.marvel.data.model.Character
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detailed.*

class DetailedActivity : BaseActivity<DetailedViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        var character = intent.getParcelableExtra<Character>("character")
        initViews(character)
    }

    private fun initViews(character: Character?) {
        var imageLink = character!!.getThumbnails()!!.getPath() + "." + character.getThumbnails()!!
            .getExtension()
        Glide.with(this)
            .load(imageLink)
            .placeholder(R.drawable.image_placeholder)
            .into(imageView)

        name.setText(character.getName())
        description.setText(character.getDescription())

        backBtn.setOnClickListener {
            onBackPressed()
        }

        if (isConnected()) {
            getComics(character.getId()!!)
            getEvents(character.getId()!!)
            getSeries(character.getId()!!)
            getStories(character.getId()!!)
        }
    }

    private fun getStories(characterId: Int) {
        getViewModel().storiesLiveData.observe(this, Observer { characterData ->
            if (characterData == null) return@Observer

            stories_recyclerView.adapter =
                CharacterDataAdapter(characterData, object : CharacterDataAdapter.OnClickCallback {
                    override fun onClick(imageLink: String) {
                        openImageDialog(imageLink)
                    }
                })
        })
        getViewModel().getCharacterData(characterId, "stories")
    }

    private fun openImageDialog(imageLink: String) {
        ViewImageDialog(this, imageLink).show()
    }

    private fun getSeries(characterId: Int) {
        getViewModel().seriesLiveData.observe(this, Observer { characterData ->
            if (characterData == null) return@Observer

            series_recyclerView.adapter =
                CharacterDataAdapter(characterData, object : CharacterDataAdapter.OnClickCallback {
                    override fun onClick(imageLink: String) {
                        openImageDialog(imageLink)
                    }
                })
        })
        getViewModel().getCharacterData(characterId, "series")
    }

    private fun getEvents(characterId: Int) {
        getViewModel().eventsLiveData.observe(this, Observer { characterData ->
            if (characterData == null) return@Observer

            events_recyclerView.adapter =
                CharacterDataAdapter(characterData, object : CharacterDataAdapter.OnClickCallback {
                    override fun onClick(imageLink: String) {
                        openImageDialog(imageLink)
                    }
                })
        })
        getViewModel().getCharacterData(characterId, "events")
    }

    private fun getComics(characterId: Int) {
        getViewModel().comicsLiveData.observe(this, Observer { characterData ->
            if (characterData == null) return@Observer

            comics_recyclerView.adapter =
                CharacterDataAdapter(characterData, object : CharacterDataAdapter.OnClickCallback {
                    override fun onClick(imageLink: String) {
                        openImageDialog(imageLink)
                    }
                })
        })
        getViewModel().getCharacterData(characterId, "comics")
    }

    override fun getViewModel(): DetailedViewModel {
        return ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
        ).get(DetailedViewModel::class.java)
    }
}