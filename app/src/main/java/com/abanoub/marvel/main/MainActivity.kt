package com.abanoub.marvel.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.abanoub.marvel.R
import com.abanoub.marvel.base.ParentActivity

class MainActivity : ParentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}