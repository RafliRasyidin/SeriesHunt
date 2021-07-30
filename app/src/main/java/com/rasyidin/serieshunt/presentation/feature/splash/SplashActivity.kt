package com.rasyidin.serieshunt.presentation.feature.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.rasyidin.serieshunt.MainActivity
import com.rasyidin.serieshunt.databinding.ActivitySplashBinding
import com.rasyidin.serieshunt.presentation.base.BaseActivity
import com.rasyidin.serieshunt.presentation.utils.Constants.SPLASH_TIME

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashDelay()
    }

    private fun splashDelay() {
        val intent = Intent(this, MainActivity::class.java)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
        }, SPLASH_TIME)
    }
}