package com.example.challenge6.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.challenge6.R
import com.example.challenge6.ViewModel.ViewModelUsers
import com.example.challenge6.model.ResponseRegister
import com.example.challenge6.network.ApiClient
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        doRegister()

    }

    fun doRegister(){
        val username = et_reg_username.text.toString()
        val email = et_reg_email.text.toString()
        val  pass = et_reg_pass.text.toString()
        btn_reg.setOnClickListener {
            if (email.isNotEmpty() && pass.isNotEmpty() && username.isNotEmpty()){
                userRegist(email, pass, username)
                startActivity(Intent(this,LoginActivity::class.java))
            }else{
                Toast.makeText(this,"Failed to register, check data again!",Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    fun postRegister(){

        val viewModel = ViewModelProvider(this).get(ViewModelUsers::class.java)
        viewModel.getRegisterLive().observe(this, Observer {
            if (it != null){
                Toast.makeText(this, "Registered", Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this, "Failed to register", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.registerUserApi(email,pass,username)
    }

//    fun userRegist(email : String, pass : String, username : String){
//        ApiClient.instance.userRegister(email,pass,username)
//            .enqueue(object : Callback<ResponseRegister>{
//                override fun onResponse(
//                    call: Call<ResponseRegister>,
//                    response: Response<ResponseRegister>
//                ) {
//                    if (response.isSuccessful){
//                        Toast.makeText(this@RegisterActivity,"Successful.",Toast.LENGTH_LONG)
//                            .show()
//                    }else{
//                        Toast.makeText(this@RegisterActivity,"Failed.",Toast.LENGTH_LONG)
//                            .show()
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
//                    Toast.makeText(this@RegisterActivity,"Failed.",Toast.LENGTH_LONG)
//                        .show()
//                }
//
//            })
//    }

}