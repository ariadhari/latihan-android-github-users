package com.example.githubusers.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.api.RetrofitClient
import com.example.githubusers.data.model.RepoUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoUserViewModel: ViewModel() {
    val listRepo = MutableLiveData<ArrayList<RepoUserResponse>>()

    fun setRepoUser(username: String){
        RetrofitClient.apiInstance
            .getRepoByUser(username)
            .enqueue(object : Callback<ArrayList<RepoUserResponse>>{
                override fun onResponse(
                    call: Call<ArrayList<RepoUserResponse>>,
                    response: Response<ArrayList<RepoUserResponse>>
                ) {
                    if (response.isSuccessful){
                        listRepo.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<RepoUserResponse>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getRepoByUser(): LiveData<ArrayList<RepoUserResponse>> {
        return listRepo
    }


}