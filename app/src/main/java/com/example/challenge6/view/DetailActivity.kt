package com.example.challenge6.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge6.R
import com.example.challenge6.model.ResponseFilmItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailFilm()

    }

    fun detailFilm(){
        val detailfilm = intent.getParcelableExtra("detailfilm") as ResponseFilmItem?
        tv_detail_title.text = detailfilm!!.title
        tv_detail_desc.text = detailfilm!!.synopsis
    }
}