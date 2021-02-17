package com.tenorinho.moviedetail.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tenorinho.moviedetail.R
import com.tenorinho.moviedetail.data.model.MovieFromSimilarList
import com.tenorinho.moviedetail.data.model.SimilarMovies

class SimilarMoviesAdapter(var similarMovies: SimilarMovies?):RecyclerView.Adapter<SimilarMoviesAdapter.SimilarMovieHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieHolder {
        return SimilarMovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))
    }
    override fun onBindViewHolder(holder: SimilarMovieHolder, position: Int) {
        holder.bind(similarMovies?.ResultsPage?.get(position))
    }
    override fun getItemCount(): Int {
        return similarMovies?.ResultsPage?.size ?: 0
    }
    class SimilarMovieHolder:RecyclerView.ViewHolder{
        val txtTitle:TextView
        val txtRelease:TextView
        val txtGenres:TextView
        val imgPoster:ImageView
        constructor(view:View):super(view){
            txtTitle = view.findViewById(R.id.item_list_movie_title)
            txtRelease = view.findViewById(R.id.item_list_movie_release_year)
            txtGenres = view.findViewById(R.id.item_list_movie_genres)
            imgPoster = view.findViewById(R.id.item_list_movie_poster)
        }
        fun bind(s:MovieFromSimilarList?){
            txtTitle.text = s?.OriginalTitle ?: "NULL"
            txtRelease.text = s?.ReleaseDate ?: "NULL"
        }
    }
    fun updateList(similarMovies: SimilarMovies?){
        this.similarMovies = similarMovies
        notifyDataSetChanged()
    }
}