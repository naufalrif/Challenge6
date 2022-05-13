package com.example.challenge6.network

import com.example.challenge6.model.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {
    @GET("apifilm.php")
    fun getAllFilm(): Call<List<ResponseFilmItem>>

    @GET("apiuser.php")
    fun getAllUser() : Call<List<GetAllUserItem>>

    @POST("register.php")
    @FormUrlEncoded
    fun userRegister(
        @Field("email")email : String,
        @Field("password")password : String,
        @Field("username")username : String
    ) : Call<ResponseRegister>

    @POST("login.php")
    @FormUrlEncoded
    fun userLogin(
        @Field("email")email : String,
        @Field("password")password : String
    ) : Call<ResponseLogin>

    @POST("updateuser.php")
    @FormUrlEncoded
    fun userUpdate(
        @Field("address")address : String,
        @Field("complete_name")complete_name : String,
        @Field("dateofbirth")dateofbirth: String,
        @Field("id")id : String,
        @Field("username")username : String
    ) : Call<ResponseUpdate>
}