package com.tenorinho.moviedetail.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_movie")
data class Movie(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val ID:Int,
    @SerializedName("original_title")
    @ColumnInfo(name="original_title")
    val OriginalTitle:String,
    @SerializedName("popularity")
    @ColumnInfo(name="popularity")
    val Popularity:Double,
    @SerializedName("vote_count")
    @ColumnInfo(name="vote_count")
    val Vote_Count:Int,
    @SerializedName("poster_path")
    @ColumnInfo(name="poster_path")
    val PosterPath:String = "",
    var PosterLocalPath:String? = null)