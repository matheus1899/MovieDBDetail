package com.tenorinho.moviedetail.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_genres")
data class Genrer(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val ID:Int,
    @SerializedName("name")
    @ColumnInfo(name = "name_genrer")
    val Name:String?)