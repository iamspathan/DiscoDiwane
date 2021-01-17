package dev.iamspathan.bookmyshowclone.data.local.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.iamspathan.bookmyshowclone.data.local.entity.Movie
import java.lang.reflect.Type
import java.util.Collections

object MovieTypeConverters {

    @TypeConverter
    @JvmStatic
    fun fromList(value: List<Movie>) = Gson().toJson(value)


    @TypeConverter
    @JvmStatic
    fun toList(value:String):List<Movie> {
        if (value == null){
            return Collections.emptyList()
        }
        val listType:Type = object :TypeToken<List<Movie>>(){}.type
        return Gson().fromJson(value, listType)
    }


}