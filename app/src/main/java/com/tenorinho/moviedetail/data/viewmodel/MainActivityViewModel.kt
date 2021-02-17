package com.tenorinho.moviedetail.data.viewmodel

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tenorinho.moviedetail.data.model.Movie
import com.tenorinho.moviedetail.data.model.SimilarMovies
import com.tenorinho.moviedetail.net.MovieDBService
import com.tenorinho.moviedetail.net.RetrofitConfig
import com.tenorinho.moviedetail.R
import com.tenorinho.moviedetail.data.repository.MovieRepository
import com.tenorinho.moviedetail.data.repository.SimilarMoviesRepository

class MainActivityViewModel() : ViewModel() {
    private val movieRepository = loadMovieRepository()
    private val similarMoviesRepository = loadSimilarMoviesRepository()

    var movie = MutableLiveData<Movie>()
    var similarMovies = MutableLiveData<SimilarMovies>()
    var error = MutableLiveData<Throwable>()
    var isLiked = MutableLiveData<Boolean>()
    var imgLikeRes = ObservableInt(R.drawable.ic_baseline_favorite_24)
    var popularityString = MutableLiveData<String>()
    var likesString = MutableLiveData<String>()


    init {
        isLiked.value = false
        loadMovie()
        loadSimilarMovies()
    }

    fun like(){
        isLiked.value = !isLiked.value!!
        if(isLiked.value!!){
            imgLikeRes.set(R.drawable.ic_baseline_favorite_24_red)
        }
        else{
            imgLikeRes.set(R.drawable.ic_baseline_favorite_24)
        }
    }
    private fun bindMovie(m:Movie?){
        movie.value = m
        popularityString.value = movie.value?.Popularity.toString()+" views"
        likesString.value = getLikesText(movie.value?.Vote_Count ?: 0)+" Likes"
    }
    private fun bindSimilarMovies(s:SimilarMovies?){
        similarMovies.value = s
    }
    private fun loadMovie(){
        movieRepository.load(this::bindMovie,{ error.value = it})
    }
    private fun loadSimilarMovies(){
        similarMoviesRepository.load(this::bindSimilarMovies, {error.value = it})
    }
    private fun loadMovieRepository(): MovieRepository {
        return MovieRepository(RetrofitConfig.getRetrofitMovieDBService().create(MovieDBService::class.java))
    }
    private fun loadSimilarMoviesRepository(): SimilarMoviesRepository {
        return SimilarMoviesRepository(RetrofitConfig.getRetrofitMovieDBService().create(MovieDBService::class.java))
    }
    //pode ser reduzida(ou não :/)
    private fun getLikesText(numLikes:Int):String{
        var likes = numLikes

        if(likes > 100000 && likes < 1000000){
            var v = 0
            while(likes > 100000){
                likes -= 100000
                v += 100
            }
            while(likes > 10000){
                likes-=10000
                v += 10
            }
            while(likes > 1000){
                likes -= 1000
                v += 1
            }
            return v.toString()+"K"
        }
        else if(likes > 10000){
            var v = 0
            while(likes > 10000){
                likes-=10000
                v += 10
            }
            while(likes > 1000){
                likes -= 1000
                v += 1
            }
            return v.toString()+"K"
        }
        else if(likes > 1000){
            var v = 0F
            while(likes > 1000){
                likes -= 1000
                v += 1F
            }
            while(likes>100){
                likes -= 100
                v += 0.1F
            }
            return v.toString()+"K"
        }
        return numLikes.toString()

    }
}