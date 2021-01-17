package dev.iamspathan.bookmyshowclone.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.iamspathan.bookmyshowclone.data.local.entity.MovieResponse

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieResponse: MovieResponse)

    @Query("SELECT * FROM tbl_movie_database")
    fun getMovies() : MovieResponse
}