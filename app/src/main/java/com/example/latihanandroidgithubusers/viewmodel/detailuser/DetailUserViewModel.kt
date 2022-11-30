package com.example.latihanandroidgithubusers.viewmodel.detailuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.latihanandroidgithubusers.data.model.DetailUserResponse
import com.example.latihanandroidgithubusers.repository.DetailUserRepository
import kotlinx.coroutines.launch


class DetailUserViewModel(private val detailUserRepository: DetailUserRepository) : ViewModel() {
    private val user = MutableLiveData<DetailUserResponse>()

    fun setUserDetail(username: String){
        // using coroutine
        viewModelScope.launch {
            try {
                val response = detailUserRepository.setUserDetail(username)
                if (response.isSuccessful){
                    user.postValue(response.body())
                }
            }
            catch (e:Exception){
                Log.d("detailuser", "setUserDetail: ${e.message}")
            }
        }
    }

    fun getUserDetail(): LiveData<DetailUserResponse>{
        return user
    }

}