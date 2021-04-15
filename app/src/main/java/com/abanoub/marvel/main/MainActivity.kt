package com.abanoub.marvel.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.abanoub.marvel.R
import com.abanoub.marvel.base.BaseActivity
import com.abanoub.marvel.data.model.Character
import com.abanoub.marvel.data.remote.SearchCharacterRemote
import com.abanoub.marvel.detailed.DetailedActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.w3c.dom.Text

class MainActivity : BaseActivity<MainViewModel>() {

    private lateinit var search_list: ArrayList<Character>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = ArrayList<Character>()
        recyclerView.adapter = CharactersAdapter(list, object : CharactersAdapter.OnItemClick {
            override fun onItemClicked(character: Character) {
                startDetailedActivity(character)
            }
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    if (getViewModel().isLoading() || getViewModel().isaLastPage()) {
                        return
                    }
                    getViewModel().getCharacters(false)
                }
            }
        })

        getViewModel().paginationLoadingProgress.observe(this, Observer { aBoolean ->
            if (aBoolean)
                setPaginationProgressViewVisibility(View.VISIBLE)
            else
                setPaginationProgressViewVisibility(View.GONE)
        })

        if (isConnected()) {
            getViewModel().charactersLiveData.observe(this, Observer { characters ->
                if (characters == null) return@Observer

                list.clear()
                list.addAll(characters)
                recyclerView.getAdapter()!!.notifyDataSetChanged()
            })
            getViewModel().getCharacters(true)
        }

        initSearch()
    }

    private fun initSearch() {
        searchBtn.setOnClickListener {
            setToolbarVisibility(View.INVISIBLE)
            setSearchConstraintLayoutVisibility(View.VISIBLE)
            setSearchRecyclerViewVisibility(View.VISIBLE)

            search_list = ArrayList()
            search_recyclerView.adapter =
                SearchAdapter(search_list, object : SearchAdapter.OnItemClick {
                    override fun onItemClicked(character: Character) {
                        startDetailedActivity(character)
                    }
                })
        }

        cancel_button.setOnClickListener {
            onCancelBtnClicked()
        }

        search_Et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                search(s.toString())
            }
        })

        getViewModel().onSearchLiveData.observe(this, Observer { characters ->
            if (characters == null) return@Observer

            search_list.clear()
            search_list.addAll(characters)
            search_recyclerView.getAdapter()?.notifyDataSetChanged()
        })
    }

    private fun onCancelBtnClicked() {
        setToolbarVisibility(View.VISIBLE)
        setSearchConstraintLayoutVisibility(View.GONE)
        setSearchRecyclerViewVisibility(View.GONE)
        setSearchViewsToDefaultState()
    }

    private fun setSearchViewsToDefaultState() {
        search_Et.setText("")
        search_list.clear()
        search_recyclerView.adapter = null
    }

    private fun search(txt: String) {
        getViewModel().search(txt)
    }

    private fun setToolbarVisibility(value: Int) {
        toolbarView.visibility = value
    }

    private fun setSearchConstraintLayoutVisibility(value: Int) {
        search_constraint.visibility = value
    }

    private fun setSearchRecyclerViewVisibility(value: Int) {
        search_recyclerView.visibility = value
    }

    private fun startDetailedActivity(character: Character) {
        var intent = Intent(this, DetailedActivity::class.java)
        intent.putExtra("character", character)
        startActivity(intent)
    }

    fun setPaginationProgressViewVisibility(value: Int) {
        progressBar.setVisibility(value)
    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
        ).get(MainViewModel::class.java)
    }

    override fun onBackPressed() {
        if (search_constraint.visibility == View.VISIBLE)
            onCancelBtnClicked()
        else
            super.onBackPressed()
    }
}