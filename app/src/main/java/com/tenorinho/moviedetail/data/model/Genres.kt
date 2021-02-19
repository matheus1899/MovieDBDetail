package com.tenorinho.moviedetail.data.model

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("genres")
    val genres:ArrayList<Genrer>)
