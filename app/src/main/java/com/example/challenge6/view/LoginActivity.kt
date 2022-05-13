package com.example.challenge6.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.challenge6.MainActivity
import com.example.challenge6.R
import com.example.challenge6.ViewModel.ViewModelUsers
import com.example.challenge6.model.GetAllUserItem
import com.example.challenge6.model.ResponseLogin
import com.example.challenge6.network.ApiClient
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var usermanager : com.example.challenge6.manager.UserManager
    lateinit var datauser : List<GetAllUserItem>
    var email = ""
    var pass = ""
    var username = ""
    var address = ""
    var fullname = ""
    var birthday = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        usermanager = com.example.challenge6.manager.UserManager(this)
        getUser()
        toRegister()
        doLogin()
    }

    fun toRegister(){
        tv_register.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

    fun doLogin(){
        btn_login.setOnClickListener {
            email = et_email.text.toString()
            pass = et_email.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                postLogin(email,pass)
            }
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    fun postLogin(email : String, password : String){
        ApiClient.instance.userLogin(email, password)
            .enqueue(object : Callback<ResponseLogin>{
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    if (response.isSuccessful){
                        for (i in datauser.indices){
                            if (email == datauser[i].email && password == datauser[i].password){
                                var id = ""
                                id = datauser[i].id
                                if (datauser[i].email != null){
                                    this@LoginActivity.email = datauser[i].email
                                }
                                if (datauser[i].password != null){
                                    this@LoginActivity.pass = datauser[i].password
                                }
                                if (datauser[i].username != null){
                                    this@LoginActivity.username = datauser[i].username
                                }
                                if (datauser[i].address != null){
                                    this@LoginActivity.address = datauser[i].address
                                }
                                if (datauser[i].dateofbirth != null){
                                    this@LoginActivity.birthday = datauser[i].dateofbirth
                                }
                                GlobalScope.launch {
                                    usermanager.saveData(id.toString(),email,password,username,address,birthday,pass)
                                }
                                Toast.makeText(this@LoginActivity,"Logged in.",Toast.LENGTH_LONG)
                                    .show()
                            }else{
                                Toast.makeText(this@LoginActivity, "Failed to login", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun getUser(){
        val viewModel = ViewModelProvider(this).get(ViewModelUsers::class.java)
        viewModel.getUserLiveData().observe(this, Observer {
            datauser = it
        })
        viewModel.getUserApi()
    }
}