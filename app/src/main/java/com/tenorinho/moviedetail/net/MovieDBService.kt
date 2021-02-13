package com.tenorinho.moviedetail.net

import com.tenorinho.moviedetail.data.model.Movie
import com.tenorinho.moviedetail.data.model.SimilarMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBService {
    @GET("{id}/")
    fun getMovieDetail(@Path("id")movieId:Int,
                       @Query("apiKey")apiKey:String): Call<Movie>

    @GET("{id}/")
    fun getSimilarMovies(@Path("id")movieId:Int,
                         @Query("apiKey")apiKey:String,
                         @Query("n_page") numberPage:Int):Call<SimilarMovies>
}