package com.abanoub.marvel.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.abanoub.marvel.R
import com.abanoub.marvel.base.BaseActivity
import com.abanoub.marvel.data.model.Character
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = ArrayList<Character>()
        recyclerView.adapter=CharactersAdapter(list,object :CharactersAdapter.OnItemClick{
            override fun onItemClicked(character: Character) {

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
}