package com.tenorinho.moviedetail.data.viewmodel

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tenorinho.moviedetail.data.model.Movie
import com.tenorinho.moviedetail.data.model.SimilarMovies
import com.tenorinho.moviedetail.R
import com.tenorinho.moviedetail.data.model.Genrer
import com.tenorinho.moviedetail.data.model.Genres
import com.tenorinho.moviedetail.data.repository.GenrerRepository
import com.tenorinho.moviedetail.data.repository.MovieRepository
import com.tenorinho.moviedetail.data.repository.SimilarMoviesRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val genrerRepository: GenrerRepository,
                            private val movieRepository: MovieRepository) : ViewModel() {
    private val similarMoviesRepository = SimilarMoviesRepository()

    val movie = MutableLiveData<Movie>()
    val similarMovies = MutableLiveData<SimilarMovies>()
    val error = MutableLiveData<Throwable>()
    val isLiked = MutableLiveData<Boolean>()
    val imgLikeRes = ObservableInt(R.drawable.ic_baseline_favorite_border_24)
    val popularityString = MutableLiveData<String>()
    val likesString = MutableLiveData<String>()
    val imgMoviePoster = MutableLiveData<String>()
    val listGenres = MutableLiveData<ArrayList<Genrer>>()
    var movieID = 62

    init {
        genrerRepository.scope = viewModelScope
        movieRepository.scope = viewModelScope
        viewModelScope.launch { genrerRepository.loadAll(::bindListGenres, ::bindError) }
        isLiked.value = false
        loadMovie()
        loadSimilarMovies()
    }
    fun like(){
        isLiked.value = !isLiked.value!!
        if(isLiked.value!!){
            imgLikeRes.set(R.drawable.ic_baseline_favorite_24)
        }
        else{
            imgLikeRes.set(R.drawable.ic_baseline_favorite_border_24)
        }
    }
    fun getGenresText(array:ArrayList<Int>?):String{
        var string:String = ""
        if(array != null){
            for(i in 0..array.size-1){
                val h = array[i].toInt()
                var g:Genrer? = null
                if(listGenres.value != null){
                    val list = listGenres.value
                    for(i in 0 until list!!.size){
                        if(h == list[i].ID){
                            g = list[i]
                        }
                    }
                }
                if(g != null){
                    string+= g.Name+", "
                }
            }
            if(string.length > 3){
                string = string.removeRange(string.length-2, string.length-1)
            }
        }
        return string
    }
    private fun bindMovie(m:Movie?){
        movie.value = m
        popularityString.value = movie.value?.Popularity.toString()+" views"
        likesString.value = getLikesText(movie.value?.Vote_Count ?: 0)+" Likes"
        imgMoviePoster.value = "https://image.tmdb.org/t/p/w500"+movie.value?.PosterPath
    }
    private fun bindSimilarMovies(s:SimilarMovies?){
        similarMovies.value = s
    }
    private fun bindListGenres(g: Genres){
        listGenres.value = g.genres
    }
    private fun bindError(t:Throwable){
        error.value = t
    }

    private fun loadMovie(){
        viewModelScope.launch {
            movieRepository.loadByID(movieID,::bindMovie,::bindError)
        }
    }
    private fun loadSimilarMovies(){
        viewModelScope.launch {
            similarMoviesRepository.loadByID(movieID, ::bindSimilarMovies, ::bindError)
        }
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
class MainActivityViewModelFactory(private val genrerRepository: GenrerRepository,
                                   private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(c: Class<T>): T {
        if (c.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(genrerRepository, movieRepository) as T
        }
        throw IllegalArgumentException("Class ViewModel desconhecida")
    }
}