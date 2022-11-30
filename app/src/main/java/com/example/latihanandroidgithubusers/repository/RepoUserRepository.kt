package com.example.latihanandroidgithubusers.repository

import com.example.latihanandroidgithubusers.api.RetrofitClient
import com.example.latihanandroidgithubusers.data.model.RepoUserResponse
import retrofit2.Response

class RepoUserRepository {
    suspend fun setRepoUser(username: String) : Response<ArrayList<RepoUserResponse>> = RetrofitClient.apiInstance.getRepoByUser(username)
}