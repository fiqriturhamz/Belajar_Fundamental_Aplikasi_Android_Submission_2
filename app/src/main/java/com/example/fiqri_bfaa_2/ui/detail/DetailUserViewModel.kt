package com.example.fiqri_bfaa_2.ui.detail

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fiqri_bfaa_2.api.RetrofitClient
import com.example.fiqri_bfaa_2.model.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {
    val user = MutableLiveData<DetailUserResponse>()
    fun setUserDetail(username: String) {
        RetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    } else {
                        Log.e(TAG, "onFailure ${response.message()}")

                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {

                    Log.e(TAG, "onFailure: ${t.message}")
                }

            })
    }

    fun getUserDetail(): LiveData<DetailUserResponse> {
        return user
    }
}