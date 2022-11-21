package com.example.latihanandroidgithubusers.viewmodel.detailuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latihanandroidgithubusers.api.RetrofitClient
import com.example.latihanandroidgithubusers.data.model.RepoUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RepoUserViewModel: ViewModel() {
    val listRepo = MutableLiveData<ArrayList<RepoUserResponse>>()

    fun setRepoUser(username: String){
        // using coroutine
        GlobalScope.launch(Dispatchers.IO){
            val response = RetrofitClient.apiInstance.getRepoByUser(username)
            if (response.isSuccessful){
                listRepo.postValue(response.body())
            }
        }
    }

    fun getRepoByUser(): LiveData<ArrayList<RepoUserResponse>> {
        return listRepo
    }

}