package com.example.fiqri_bfaa_2.api

import com.example.fiqri_bfaa_2.BuildConfig
import com.example.fiqri_bfaa_2.model.DetailUserResponse
import com.example.fiqri_bfaa_2.model.User
import com.example.fiqri_bfaa_2.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("search/users")
    @Headers("Authorization:token ${BuildConfig.KEY}")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization:token ${BuildConfig.KEY}")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization:token ${BuildConfig.KEY}")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization:token ${BuildConfig.KEY}")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>


}