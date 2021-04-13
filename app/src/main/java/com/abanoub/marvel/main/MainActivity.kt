package com.abanoub.marvel.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.abanoub.marvel.R
import com.abanoub.marvel.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
        ).get(MainViewModel::class.java)
    }
}