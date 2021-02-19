package com.tenorinho.moviedetail.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tenorinho.moviedetail.data.model.Genrer
import com.tenorinho.moviedetail.data.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities=arrayOf(Genrer::class, Movie::class), version = 1, exportSchema=false)
abstract class LocalDB : RoomDatabase() {
    abstract fun genrerDAO():GenrerDAO
    abstract fun movieDAO(): MovieDAO

    companion object{
        //Singleton
        @Volatile private var INSTANCIA: LocalDB? = null
        fun getDatabase(context: Context, scope: CoroutineScope): LocalDB {
            return INSTANCIA ?: synchronized(this){
                val instance: LocalDB = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDB::class.java,
                    "local_database"
                ).addCallback(LocalDBCallback(scope)).build()
                INSTANCIA = instance
                instance
            }
        }
    }
    private class LocalDBCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCIA?.let { database -> scope.launch { } }
        }
    }
}