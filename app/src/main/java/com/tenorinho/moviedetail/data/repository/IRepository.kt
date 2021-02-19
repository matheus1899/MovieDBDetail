package com.tenorinho.moviedetail.data.repository

interface IRepository<T> {
    fun load(success:(T?)->Unit, failure:(Throwable) -> Unit)
    suspend fun loadByID(id:Int, success:(T?)->Unit, failure:(Throwable) -> Unit)
}