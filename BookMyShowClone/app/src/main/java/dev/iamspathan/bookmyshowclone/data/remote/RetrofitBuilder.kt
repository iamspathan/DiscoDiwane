package dev.iamspathan.bookmyshowclone.data.remote

import com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val client = OkHttpClient.Builder().build()

    private val gson :Gson = GsonBuilder()
        .setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    fun buildService(): MovieService{
        return retrofit.create(MovieService::class.java)
    }
}