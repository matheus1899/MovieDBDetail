package com.tenorinho.moviedetail.data.repository

import com.tenorinho.moviedetail.data.model.Movie
import com.tenorinho.moviedetail.net.MovieDBService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val service:MovieDBService): IRepository<Movie> {
    override fun load(success: (Movie?) -> Unit, failure:(Throwable) -> Unit){
        val callback = service.getMovieDetail(62,"c8bf1b2fd363b932dd146aa1547f990d")

        callback.enqueue(object : Callback<Movie?> {
            override fun onFailure(call: Call<Movie?>, t: Throwable) {
                failure(t)
            }
            override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                success(response.body())
            }
        })
    }
}