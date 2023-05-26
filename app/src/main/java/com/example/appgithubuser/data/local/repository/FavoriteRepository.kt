package com.example.appgithubuser.data.local.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.appgithubuser.data.local.database.Favorite
import com.example.appgithubuser.data.local.database.FavoriteDao
import com.example.appgithubuser.data.local.database.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavoriteDao : FavoriteDao
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }
    fun getAllFavorite(): List<Favorite> = mFavoriteDao.getAllDataFavorite()

    fun insert(favorite: Favorite) {
        executorService.execute { mFavoriteDao.insert(favorite) }
    }
    fun delete(favorite: Favorite) {
        executorService.execute { mFavoriteDao.delete(favorite) }
    }
    fun update(favorite: Favorite) {
        executorService.execute { mFavoriteDao.update(favorite) }
    }
    fun getFavoriteByUsername(username: String): Favorite = mFavoriteDao.getFavoriteByUsername(username)
}