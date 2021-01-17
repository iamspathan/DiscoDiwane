package dev.iamspathan.bookmyshowclone.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import dev.iamspathan.bookmyshowclone.data.local.typeconverters.MovieTypeConverters

@Entity(tableName = "tbl_movie_database")
data class MovieResponse(

    @PrimaryKey
    val page:Int = 1,

    @ColumnInfo(name = "movie_response")
    @TypeConverters(MovieTypeConverters::class)
    @Embedded
    val list: List<Movie>

)