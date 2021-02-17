package com.tenorinho.moviedetail.data.repository

import com.tenorinho.moviedetail.data.model.SimilarMovies
import com.tenorinho.moviedetail.net.MovieDBService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SimilarMoviesRepository(private val service:MovieDBService):IRepository<SimilarMovies>{
    override fun load(success: (SimilarMovies?) -> Unit, failure: (Throwable) -> Unit) {
        val callback : Call<SimilarMovies> = service.getSimilarMovies(62,"c8bf1b2fd363b932dd146aa1547f990d",1)

        callback.enqueue(object : Callback<SimilarMovies?> {
            override fun onFailure(call: Call<SimilarMovies?>, t: Throwable) {
                failure(t)
            }
            override fun onResponse(call: Call<SimilarMovies?>, response: Response<SimilarMovies?>) {
                success(response.body())
            }
        })

    }
}