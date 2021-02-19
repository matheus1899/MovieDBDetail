package com.tenorinho.moviedetail.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tenorinho.moviedetail.data.model.Genrer
import com.tenorinho.moviedetail.data.model.Movie

@Dao interface MovieDAO {
    @Query("SELECT * FROM table_movie WHERE id LIKE :MovieID")
    suspend fun getMovieById(MovieID:Int): Movie?
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)
}