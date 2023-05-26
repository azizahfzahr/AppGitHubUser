package com.example.appgithubuser.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.appgithubuser.data.local.database.Favorite
import com.example.appgithubuser.data.local.repository.FavoriteRepository

class FavoriteViewModel (application: Application) : ViewModel() {

    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    // fun getFavoriteByUsername(): LiveData<Favorite> = mFavoriteRepository.getAllFavorite()

  //  fun getAllFavorite(): LiveData<Favorite> = mFavoriteRepository.getAllFavorite()

    fun insert(favorite: Favorite) {
      mFavoriteRepository.insert(favorite) }

//    fun delete(favorite: Favorite){
//        mFavoriteRepository.delete(favorite)
//    }

    }