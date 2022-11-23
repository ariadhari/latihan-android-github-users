package com.example.latihanandroidgithubusers.viewmodel.detailuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.latihanandroidgithubusers.api.RetrofitClient
import com.example.latihanandroidgithubusers.data.model.RepoUserResponse
import com.example.latihanandroidgithubusers.repository.RepoUserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RepoUserViewModel(private val repoUserRepository: RepoUserRepository): ViewModel() {
    private val listRepo = MutableLiveData<ArrayList<RepoUserResponse>>()

    fun setRepoUser(username: String){
        // using coroutine
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiInstance.getRepoByUser(username)
                if (response.isSuccessful){
                    listRepo.postValue(response.body())
                }
            }
            catch (e:Exception){
                Log.d("repouser", "setRepoUser: ${e.message}")
            }

        }
    }

    fun getRepoByUser(): LiveData<ArrayList<RepoUserResponse>> {
        return listRepo
    }

}