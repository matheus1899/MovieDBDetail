package com.tenorinho.moviedetail.data.model

import com.google.gson.annotations.SerializedName

data class MovieFromSimilarList(
    @SerializedName("id")
    val ID:Int,
    @SerializedName("original_title")
    val OriginalTitle:String,
    @SerializedName("popularity")
    val Popularity:Double,
    @SerializedName("vote_count")
    val Vote_Count:Int,
    @SerializedName("poster_path")
    val PosterPath:String?,
    @SerializedName("genre_ids")
    var GenreIDs:ArrayList<Int>,
    @SerializedName("release_date")
    var ReleaseDate:String,
    var PosterLocalPath:String? = null)