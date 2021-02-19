package com.tenorinho.moviedetail.data.repository

import com.tenorinho.moviedetail.data.db.GenrerDAO
import com.tenorinho.moviedetail.data.model.Genrer
import com.tenorinho.moviedetail.data.model.Genres
import com.tenorinho.moviedetail.net.MovieDBService
import com.tenorinho.moviedetail.net.RetrofitConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenrerRepository(private val genrerDAO: GenrerDAO){
    private val service by lazy{ RetrofitConfig.getRetrofitMovieDBService().create(MovieDBService::class.java)}
    var scope:CoroutineScope? = null

    suspend fun loadByID(id:Int, success:(Genrer?)->Unit, failure:(Throwable) -> Unit) {
        var g = genrerDAO.getGenreById(id)
        if(g == null){
            val callback = service?.getGenreMovieList("c8bf1b2fd363b932dd146aa1547f990d")
            callback?.enqueue(object: Callback<Genres>{
                override fun onResponse( call: Call<Genres>, response: Response<Genres>) {
                    val genres = response.body()
                    genres?.genres?.forEach {
                        if(it.ID == id){
                            g = it
                        }
                        scope?.launch { insert(it) }
                    }
                    success(g)
                }
                override fun onFailure(call: Call<Genres>, t: Throwable) {
                    failure(t)
                }
            })
        }
        else{
            success(g)
        }
    }
    suspend fun loadAll(success:(Genres)->Unit, failure:(Throwable) -> Unit){
        val list = Genres(ArrayList<Genrer>(genrerDAO.getAllGenres()))
        if(list == null || list.genres.isEmpty()){
            val callback = service?.getGenreMovieList("c8bf1b2fd363b932dd146aa1547f990d")
            callback?.enqueue(object: Callback<Genres>{
                override fun onResponse( call: Call<Genres>, response: Response<Genres>) {
                    val genres = response.body()
                    if(genres == null){
                        failure(Throwable(response.message()))
                    }
                    else{
                        success(genres)
                    }
                }
                override fun onFailure(call: Call<Genres>, t: Throwable) {
                    failure(t)
                }
            })
        }
        else{
            success(list)
        }
    }
    suspend fun insert(genrer:Genrer){
        genrerDAO.insert(genrer)
    }
}