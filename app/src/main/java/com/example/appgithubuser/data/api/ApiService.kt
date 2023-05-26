package com.example.appgithubuser.data.api

import com.example.appgithubuser.data.api.response.DetailUserResponse
import com.example.appgithubuser.data.api.response.SearchResponse
import com.example.appgithubuser.data.api.response.User
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("search/users")
    fun searchUser(
        @Query("q") query: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{login}/followers")
    fun getDetailFollower(@Path("login") username: String
    ): Call<List<User>>

    @GET("users/{login}/following")
    fun getDetailFollowing(@Path("login") username: String
    ): Call<List<User>>
}