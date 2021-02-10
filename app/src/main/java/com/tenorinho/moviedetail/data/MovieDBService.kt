package com.tenorinho.moviedetail.data

import com.tenorinho.moviedetail.data.model.Movie
import com.tenorinho.moviedetail.data.model.SimilarMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDBService {
    @GET("movie/{id}?api_key={apiKey}")
    fun getMovieDetail(@Path("id")movieId:Int,
                       @Path("apiKey")apiKey:String): Call<Movie>

    @GET("movie/{id}/similar?api_key={apiKey}&page={n_page}")
    fun getSimilarMovies(@Path("id")movieId:Int,
                         @Path("apiKey")apiKey:String,
                         @Path("n_page") numberPage:Int):Call<SimilarMovies>
}