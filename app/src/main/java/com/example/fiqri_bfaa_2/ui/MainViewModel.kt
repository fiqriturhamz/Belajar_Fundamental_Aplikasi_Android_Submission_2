package com.example.fiqri_bfaa_2.ui


import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fiqri_bfaa_2.api.RetrofitClient
import com.example.fiqri_bfaa_2.model.User
import com.example.fiqri_bfaa_2.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val listUsers = MutableLiveData<List<User>>()
    fun setSearchUsers(query: String) {
        RetrofitClient.apiInstance
            .getSearchUser(query)
            .enqueue(object : Callback<UserResponse> {


                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUsers.value = response.body()?.items
                    } else {
                        Log.e(TAG, "onFailure ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Failure", "${t.message}")
                }

            })
    }

    fun getSearchUsers(): LiveData<List<User>> {
        return listUsers
    }
}