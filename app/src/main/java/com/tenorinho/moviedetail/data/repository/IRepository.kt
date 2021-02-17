package com.tenorinho.moviedetail.data.repository

interface IRepository<T> {
    fun load(success:(T?)->Unit, failure:(Throwable) -> Unit)
}