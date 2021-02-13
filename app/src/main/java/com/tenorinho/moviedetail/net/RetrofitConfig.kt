package com.tenorinho.moviedetail.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    companion object{
        fun getRetrofitMovieDBService(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}