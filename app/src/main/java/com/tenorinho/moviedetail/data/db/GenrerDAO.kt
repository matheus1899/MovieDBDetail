package com.tenorinho.moviedetail.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tenorinho.moviedetail.data.model.Genrer

@Dao interface GenrerDAO {
    @Query("SELECT * FROM table_genres")
    suspend fun getAllGenres():List<Genrer>
    @Query("SELECT * FROM table_genres WHERE id LIKE :GenreID")
    suspend fun getGenreById(GenreID:Int):Genrer?
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(genrer:Genrer)
}