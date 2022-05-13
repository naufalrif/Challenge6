package com.example.challenge6.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challenge6.model.ResponseFilmItem
import com.example.challenge6.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm : ViewModel() {
    var livedataFilm : MutableLiveData<List<ResponseFilmItem>> = MutableLiveData()

    fun getfilmLiveData():MutableLiveData<List<ResponseFilmItem>>{
        return livedataFilm
    }

    fun getFilmApi(){
        ApiClient.instance.getAllFilm()
            .enqueue(object : Callback<List<ResponseFilmItem>>{
                override fun onResponse(
                    call: Call<List<ResponseFilmItem>>,
                    response: Response<List<ResponseFilmItem>>
                ) {
                    if (response.isSuccessful){
                        livedataFilm.postValue(response.body())
                    }else{
                        livedataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseFilmItem>>, t: Throwable) {
                    livedataFilm.postValue(null)
                }

            })
    }

}