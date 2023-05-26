package com.example.appgithubuser.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.appgithubuser.R
import com.example.appgithubuser.databinding.ActivityDetailBinding
import com.example.appgithubuser.ui.detail.DetailViewModel

class FavoriteActivity : AppCompatActivity() {

    private val detailViewModel by viewModels<DetailViewModel>(){
        ViewModelFactory.getInstance(application)
    }
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(R.layout.activity_favorite)
    }
}