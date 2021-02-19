package com.tenorinho.moviedetail.data.repository

import com.tenorinho.moviedetail.data.db.MovieDAO
import com.tenorinho.moviedetail.data.model.Movie
import com.tenorinho.moviedetail.net.MovieDBService
import com.tenorinho.moviedetail.net.RetrofitConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val movieDAO: MovieDAO): IRepository<Movie> {
    private val service by lazy{ RetrofitConfig.getRetrofitMovieDBService().create(MovieDBService::class.java)}
    var scope: CoroutineScope? = null

    override fun load(success: (Movie?) -> Unit, failure:(Throwable) -> Unit){
        throw Exception("Não implementado")
    }
    override suspend fun loadByID(id: Int, success: (Movie?) -> Unit, failure: (Throwable) -> Unit) {
        val m = movieDAO.getMovieById(id)

        if(m == null){
            val callback = service.getMovieDetail(id,"c8bf1b2fd363b932dd146aa1547f990d")

            callback.enqueue(object : Callback<Movie?> {
                override fun onFailure(call: Call<Movie?>, t: Throwable) {
                    failure(t)
                }
                override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                    val movie = response.body()
                    if(movie != null){
                        scope?.launch {
                            movieDAO.insert(movie)
                        }
                        success(movie)
                    }
                    else{
                        failure(Throwable(response.message()))
                    }
                }
            })
        }
        else{
            success(m)
        }
    }
}