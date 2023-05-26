package com.example.appgithubuser.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.appgithubuser.R
import com.example.appgithubuser.adapter.SectionsPagerAdapter
import com.example.appgithubuser.data.local.database.Favorite
import com.example.appgithubuser.databinding.ActivityDetailBinding
import com.example.appgithubuser.favorite.FavoriteViewModel
import com.example.appgithubuser.favorite.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var favoriteViewModel: FavoriteViewModel
    private var isFavorite: Boolean = false
    private val detailViewModel by viewModels<DetailViewModel>(){
        ViewModelFactory.getInstance(application)
    }
    private lateinit var username: String
    private lateinit var avatarUrl : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        val name = intent.getStringExtra("username")
        Log.d("iniData", name.toString())

        val avatar = intent.getStringExtra("avatar")
        Log.d("iniAvatar", name.toString())

        if (name != null) {
            detailViewModel.getFavoriteByUsername(name)
        }
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

        if (name != null) {
            detailViewModel.getUser(name)
        }

        detailViewModel.listUser.observe(this){
            binding.namaProfile.text = it.name
            binding.usernameProfile.text = it.login
            binding.followers.text = it.followers.toString()
            binding.following.text = it.following.toString()
            username = it.login
            avatarUrl = it.avatarUrl

            Glide.with(this)
                .load(it.avatarUrl)
                .into(binding.photoProfile)
        }

        detailViewModel.isLoading.observe(this){
            showLoading(it)
        }

        detailViewModel.favoriteUsername.observe(this){ data ->
            if(data != null){
                checkFavorited(true)
                isFavorite = true
            }else{
                checkFavorited(false)
                isFavorite = false
            }

        }

        binding.fabFavorite.setOnClickListener{
            isFavorited(isFavorite)
        }

        favoriteViewModel = obtainViewModel(this)

        binding.fabFavorite.setOnClickListener{
            val data = Favorite(username = username, avatar = avatarUrl)
            detailViewModel.insert(data)
        }

//        binding.fabFavorite.setOnClickListener{
//            val data = Favorite(username = username, avatar = avatarUrl)
//            favoriteViewModel.delete(data)
//        }

    }
//    if (username != null) {
//
//        detailViewModel.detailUser.observe(this) {
//            setUserData(it)
//        }
//        detailViewModel.isLoading.observe(this, {
//            showLoading(it)
//        })
//        detailViewModel.findDetail(username)
//        detailViewModel.getFavoriteUserByUsername(username).observe(this, {
//            if (it == null){
//                binding.fabFavorite.apply {
//                    setOnClickListener {
//                        detailViewModel.insert(favorite)
//                    }
//                    setImageResource(R.drawable.baseline_favorite_border_24)
//                }
//            }else{
//                binding.fabFavorite.apply {
//                    setOnClickListener {
//                        detailViewModel.delete(favorite)
//                    }
//                    setImageResource(R.drawable.baseline_favorite_24)
//                }
//            }
//        })

    private fun checkFavorited(favorite: Boolean){
        if(favorite){
            binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
        }else{
            binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24_black)
        }
    }

    private fun isFavorited(favorite: Boolean){
        val dataavatarUrl: String? = intent?.getStringExtra("avatarUrl")
        val datausername: String? = intent?.getStringExtra("username")
        val favoriteData = datausername?.let { Favorite(it, dataavatarUrl) }

        if(favorite){
            if (favoriteData != null) {
                detailViewModel.insert(favoriteData)
            }
            if (favoriteData != null) {
                showToast("${favoriteData.username} ditambahkan ke favorite")
            }
        }else{
            if (favoriteData != null) {
                detailViewModel.delete(favoriteData)
            }
            if (favoriteData != null) {
                showToast("${favoriteData.username} dihapus dari favorite")
            }
        }
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading){
            binding.pbUser.visibility = View.VISIBLE
        }else{
            binding.pbUser.visibility = View.INVISIBLE
        }
    }

    fun getData(username:String){detailViewModel.detailUser(username)}


    fun fetchData(){
        detailViewModel.listUser.observe(this){dataApi->

            binding.namaProfile.text = dataApi.name
            binding.usernameProfile.text = dataApi.login
            binding.followers.text = dataApi.followers.toString()
            binding.following.text = dataApi.following.toString()

            Glide.with(this)
                .load(dataApi.avatarUrl)
                .into(binding.photoProfile)
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteViewModel::class.java)
    }
}