package dev.iamspathan.bookmyshowclone.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.gson.internal.bind.DateTypeAdapter
import dev.iamspathan.bookmyshowclone.data.local.dao.MovieDao
import dev.iamspathan.bookmyshowclone.data.local.entity.MovieResponse

@Database(entities = [MovieResponse::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao():MovieDao

    companion object {
        private const val DATABASE_NAME = "movie-app"

        @Volatile
        private var INSTANCE : MovieDatabase? = null

        fun getInstance(context:Context):MovieDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                    INSTANCE=instance
                }
            return instance
            }
        }
    }

}