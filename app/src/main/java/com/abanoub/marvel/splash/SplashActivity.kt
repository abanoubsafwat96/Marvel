package com.abanoub.marvel.splash

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.abanoub.marvel.main.MainActivity
import com.abanoub.marvel.R

class SplashActivity : AppCompatActivity() {
    private val SPLASH_DISPLAY_LENGTH = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        Handler().postDelayed({
                val mainIntent = Intent(this, MainActivity::class.java)
                this.startActivity(mainIntent)
                this.finish()
            }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}