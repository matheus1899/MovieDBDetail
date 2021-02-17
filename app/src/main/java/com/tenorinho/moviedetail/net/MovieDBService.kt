package com.tenorinho.moviedetail.net

import com.tenorinho.moviedetail.data.model.Movie
import com.tenorinho.moviedetail.data.model.SimilarMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBService {
    @GET("{id}?")
    fun getMovieDetail(@Path("id")movieId:Int,
                       @Query("api_key")apiKey:String): Call<Movie>

    @GET("{id}/similar?")
    fun getSimilarMovies(@Path("id")movieId:Int,
                         @Query("api_key")apiKey:String,
                         @Query("n_page") numberPage:Int):Call<SimilarMovies>
}