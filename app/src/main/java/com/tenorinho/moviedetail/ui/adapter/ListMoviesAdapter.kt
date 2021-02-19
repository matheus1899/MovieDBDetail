package com.tenorinho.moviedetail.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tenorinho.moviedetail.data.model.MovieFromSimilarList
import com.tenorinho.moviedetail.data.model.SimilarMovies
import com.tenorinho.moviedetail.data.viewmodel.MainActivityViewModel
import com.tenorinho.moviedetail.databinding.ItemListMovieBinding

class ListMoviesAdapter(var similarMovies: SimilarMovies?, val viewModel: MainActivityViewModel)
    :RecyclerView.Adapter<ListMoviesAdapter.SimilarMovieHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieHolder {
        return SimilarMovieHolder(ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: SimilarMovieHolder, position: Int) {
        holder.bind(similarMovies?.ResultsPage?.get(position), viewModel)
    }
    override fun getItemCount(): Int {
        return similarMovies?.ResultsPage?.size ?: 0
    }

    class SimilarMovieHolder:RecyclerView.ViewHolder{
        val binding:ItemListMovieBinding
        constructor(binding: ItemListMovieBinding):super(binding.root){
            this.binding = binding
        }
        fun bind(s:MovieFromSimilarList?, viewModel: MainActivityViewModel){
            if(s != null){
                binding.title = s.OriginalTitle
                binding.releaseDate = s.ReleaseDate
                binding.viewModel = viewModel
                binding.genreIds = s.GenreIDs
                Glide
                    .with(binding.itemListMoviePoster.context)
                    .load("https://image.tmdb.org/t/p/w200"+s.PosterPath)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.itemListMoviePoster)
            }
        }
    }
    fun updateList(similarMovies: SimilarMovies?){
        this.similarMovies = similarMovies
        notifyDataSetChanged()
    }
}