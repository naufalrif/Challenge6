package com.example.challenge6.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challenge6.R
import com.example.challenge6.model.ResponseFilmItem
import kotlinx.android.synthetic.main.item_film.view.*

class FilmAdapter(private var onCLick : (ResponseFilmItem)->Unit) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
    private var datafilm : List<ResponseFilmItem>? = null

    fun setDataFilm(film : List<ResponseFilmItem>){
        this.datafilm = film
    }

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film, parent, false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_judulfilm.text = datafilm!![position].title
        holder.itemView.tv_tanggalfilm.text = datafilm!![position].releaseDate
        holder.itemView.tv_sutradarafilm.text = datafilm!![position].director
        Glide.with(holder.itemView.context).load(datafilm!![position].image).into(holder.itemView.img_film)
        holder.itemView.cardfilm.setOnClickListener {
            onCLick(datafilm!![position])
        }
    }

    override fun getItemCount(): Int {
        if (datafilm == null){
            return 0
        }
        else{
            return datafilm?.size!!
        }
    }
}