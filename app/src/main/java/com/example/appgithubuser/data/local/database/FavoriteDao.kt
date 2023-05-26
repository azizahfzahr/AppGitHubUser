package com.example.appgithubuser.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)

    @Update
    fun update(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)

    @Query("SELECT * FROM Favorite WHERE username = :username")
    fun getFavoriteByUsername(username: String): Favorite

    @Query("SELECT * FROM Favorite")
    fun getAllDataFavorite(): List<Favorite>
}









//    @Query("SELECT * from Favorite ORDER BY username ASC")
//    fun getAllFavorite(): LiveData<List<Favorite>>

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    abstract fun addToFavorite(favorite: Favorite)
//
//    @Query("SELECT * FROM favorite_user")
//    fun getFavoriteUser(): LiveData<List<Favorite>>
//
//    @Query("SELECT count(*) FROM favorite_user WHERE favorite_user.id = :id")
//    suspend fun checkUser(id: Int): Int
//
//    @Query("DELETE FROM favorite_user WHERE favorite_user.id = :id")
//    suspend fun removeFromFavorite(id: Int): Int