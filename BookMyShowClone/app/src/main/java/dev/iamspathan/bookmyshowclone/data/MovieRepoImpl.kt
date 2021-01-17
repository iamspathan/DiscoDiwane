package dev.iamspathan.bookmyshowclone.data

import dev.iamspathan.bookmyshowclone.data.local.dao.MovieDao
import dev.iamspathan.bookmyshowclone.data.local.entity.MovieResponse
import dev.iamspathan.bookmyshowclone.data.remote.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepoImpl (

    private val movieDao: MovieDao,
    private val movieService: MovieService
) : MovieRepository {

    override fun getMoviesRemote(apiKey: String, onSuccess: (MovieResponse) -> Unit, onError: (String) -> Unit) {
        val response = movieService.getMovies(apiKey)

        response.enqueue(object :Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful && response.body() != null){
                    Thread {
                        movieDao.insertMovies(response.body()!!)
                        onSuccess(response.body()!!)
                    }.start()
                }else {
                    onError(response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                onError(t.localizedMessage?: "Something went Wrong")
            }
        })
    }

    override fun getMoviesLocal(onSuccess: (MovieResponse?) -> Unit) {
        Thread {
            onSuccess(movieDao.getMovies())
        }.start()
    }
}