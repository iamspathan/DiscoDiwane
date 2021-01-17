package dev.iamspathan.bookmyshowclone.data

import dev.iamspathan.bookmyshowclone.data.local.entity.MovieResponse

interface MovieRepository {

    fun getMoviesRemote(apiKey:String, onSuccess:(MovieResponse) -> Unit, onError:(String) -> Unit)

    fun getMoviesLocal(onSuccess: (MovieResponse?) -> Unit )
}