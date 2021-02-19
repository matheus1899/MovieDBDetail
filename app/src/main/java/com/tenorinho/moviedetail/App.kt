package com.tenorinho.moviedetail

import android.app.Application
import com.tenorinho.moviedetail.data.db.LocalDB
import com.tenorinho.moviedetail.data.repository.GenrerRepository
import com.tenorinho.moviedetail.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App:Application() {
    private val appScope: CoroutineScope = CoroutineScope(SupervisorJob())

    private val database: LocalDB by lazy { LocalDB.getDatabase(this, appScope) }
    val genrerRepository:GenrerRepository by lazy { GenrerRepository(database.genrerDAO()) }
    val movieRepository:MovieRepository by lazy { MovieRepository(database.movieDAO()) }
}