package com.example.appgithubuser.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appgithubuser.data.api.response.DetailUserResponse
import com.example.appgithubuser.data.api.response.User
import com.example.appgithubuser.data.local.database.Favorite
import com.example.appgithubuser.data.api.ApiConfig
import com.example.appgithubuser.data.local.repository.FavoriteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : ViewModel(){

    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    private val _listUser = MutableLiveData<DetailUserResponse>()
    val listUser : LiveData<DetailUserResponse> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isLoadingFollow = MutableLiveData<Boolean>()
    val isLoadingFollow : LiveData<Boolean> = _isLoadingFollow

    private val _listFollower = MutableLiveData<List<User>>()
    val listFollower : LiveData<List<User>> = _listFollower

    private val _listFollowing = MutableLiveData<List<User>>()
    val listFollowing : LiveData<List<User>> = _listFollowing

    private val _favoriteUsername = MutableLiveData<Favorite>()
    val favoriteUsername : LiveData<Favorite> = _favoriteUsername


    fun insert(favorite: Favorite) {
        mFavoriteRepository.insert(favorite)
    }
    fun update(favorite: Favorite) {
        mFavoriteRepository.update(favorite)
    }
    fun delete(favorite: Favorite) {
        mFavoriteRepository.delete(favorite)
    }
    fun getFavoriteByUsername(username: String){
        _favoriteUsername.value = mFavoriteRepository.getFavoriteByUsername(username)
    }
//    fun getFavoriteByUsername(username: String){
//        mFavoriteRepository.getAllFavorite()
//    }

//    private var userDao: FavoriteDao?
//    private var userDb: UserDatabase?
//
//    init {
//        userDb = UserDatabase.getDatabase(application)
//        userDao = userDb?.favoriteDao()
//    }

    fun getUser(username: String){
        detailUser(username)
        getFollower(username)
        getFollowing(username)
    }

    fun detailUser(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object: Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>,
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUser.value = response.body()
                } else {
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {

            }

        })
    }

    fun getFollower(username: String){
        _isLoadingFollow.value = true
        val client = ApiConfig.getApiService().getDetailFollower(username)
        client.enqueue(object: Callback<List<User>>{
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>,
            ) {
                _isLoadingFollow.value = false
                if (response.isSuccessful) {
                    _listFollower.value = response.body()
                } else {
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {

            }

        })
    }

    fun getFollowing(username: String){
        _isLoadingFollow.value = true
        val client = ApiConfig.getApiService().getDetailFollowing(username)
        client.enqueue(object: Callback<List<User>>{
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>,
            ) {
                _isLoadingFollow.value = false
                if (response.isSuccessful) {
                    _listFollowing.value = response.body()
                } else {
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {

            }

        })
    }

//
//     fun addToFavorite(username: String, id: Int) {
//         CoroutineScope(Dispatchers.IO).launch {
//             var user = Favorite(
//                 username,
//                 id
//             )
//             userDao?.addToFavorite(user)
//         }
//     }
//    suspend fun checkUser(id: Int) = userDao?.checkUser(id)
//
//    fun removeFromFavorite(id: Int){
//        CoroutineScope(Dispatchers.IO).launch {
//            userDao?.removeFromFavorite(id)
//        }
//    }
}