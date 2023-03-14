package com.example.quizesfand

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET


interface GithubApiService {
    @GET("users/MENasri23")
    fun getUser(): Call<User>
}

data class User(
    @SerializedName("avatar_url") val avatarUrl: String
)