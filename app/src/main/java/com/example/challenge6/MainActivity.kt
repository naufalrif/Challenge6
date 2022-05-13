package com.example.challenge6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserManager
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge6.ViewModel.ViewModelFilm
import com.example.challenge6.adapter.FilmAdapter
import com.example.challenge6.model.ResponseFilmItem
import com.example.challenge6.view.DetailActivity
import com.example.challenge6.view.ProfileActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var usermanager : com.example.challenge6.manager.UserManager
    lateinit var adapterfilm : FilmAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usermanager = com.example.challenge6.manager.UserManager(this)
        usermanager.userUsername.asLiveData().observe(this,{
            tv_welcomeuser.text = "Welcome, " + it.toString()
        })
        initRecyclerView()
        profilePage()
        runOnUiThread {  }
    }


    fun filmViewModel(){
        val viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewModel.getfilmLiveData().observe(this, Observer {
            if (it != null){
                adapterfilm.setDataFilm(it)
                adapterfilm.notifyDataSetChanged()
            }
        })
        viewModel.getFilmApi()
    }

    fun initRecyclerView(){
        adapterfilm = FilmAdapter {
            val pindah = Intent(this,DetailActivity::class.java)
            pindah.putExtra("detailfilm",it)
            startActivity(pindah)
        }
        rv_film.layoutManager = LinearLayoutManager(this)
        rv_film.adapter = adapterfilm
        filmViewModel()
    }

    fun profilePage(){
        btn_profile.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }
    }

//    fun userLogout(){
//
//    }

}


