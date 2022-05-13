package com.example.challenge6.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challenge6.model.GetAllUserItem
import com.example.challenge6.model.ResponseLogin
import com.example.challenge6.model.ResponseRegister
import com.example.challenge6.model.ResponseUpdate
import com.example.challenge6.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUsers: ViewModel() {
    var livedataUser : MutableLiveData<List<GetAllUserItem>> = MutableLiveData()
    var livedataUpdate : MutableLiveData<ResponseUpdate> = MutableLiveData()
    var livedataLogin : MutableLiveData<ResponseLogin> = MutableLiveData()
    var livedataRegister : MutableLiveData<ResponseRegister> = MutableLiveData()

    fun getUserLiveData():MutableLiveData<List<GetAllUserItem>>{
        return livedataUser
    }

    fun getRegisterLive():MutableLiveData<ResponseRegister>{
        return livedataRegister
    }

    fun getLoginLive():MutableLiveData<ResponseLogin>{
        return livedataLogin
    }

    fun getUpdateLive():MutableLiveData<ResponseUpdate>{
        return livedataUpdate
    }

    fun getUserApi(){
        ApiClient.instance.getAllUser()
            .enqueue(object : Callback<List<GetAllUserItem>> {
                override fun onResponse(call: Call<List<GetAllUserItem>>, response: Response<List<GetAllUserItem>>) {
                    if (response.isSuccessful){
                        livedataUser.postValue(response.body())
                    }else{
                        livedataUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetAllUserItem>>, t: Throwable) {
                    livedataUser.postValue(null)
                }

            })
    }

    fun registerUserApi(email : String, pass : String, username : String){
        ApiClient.instance.userRegister(email,pass, username)
            .enqueue(object : Callback<ResponseRegister>{
                override fun onResponse(
                    call: Call<ResponseRegister>,
                    response: Response<ResponseRegister>
                ) {
                    if (response.isSuccessful){
                        livedataRegister.postValue(response.body())
                    }else{
                        livedataRegister.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                    livedataRegister.postValue(null)
                }

            })

    }

//    fun loginUserApi(email : String, pass: String){
//        ApiClient.instance.userLogin(email,pass)
//            .enqueue(object : Callback<ResponseLogin>{
//                override fun onResponse(
//                    call: Call<ResponseLogin>,
//                    response: Response<ResponseLogin>
//                ) {
//                    if (response.isSuccessful){
//                        for (i in )
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//            })
//    }
}