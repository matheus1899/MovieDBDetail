package com.tenorinho.moviedetail.data.model

import com.google.gson.annotations.SerializedName

data class SimilarMovies(
    @SerializedName("page")
    var Page:Int,
    @SerializedName("total_pages")
    val TotalPages:Int,
    @SerializedName("total_results")
    val TotalResults:Int,
    @SerializedName("results")
    val ResultsPage:ArrayList<MovieFromSimilarList>)