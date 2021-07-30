package com.rasyidin.serieshunt.presentation.feature.detail

import android.os.Bundle
import com.rasyidin.serieshunt.databinding.ActivityDetailBinding
import com.rasyidin.serieshunt.presentation.base.BaseActivity

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override fun getViewBinding(): ActivityDetailBinding =
        ActivityDetailBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}