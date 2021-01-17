package dev.iamspathan.bookmyshowclone.ui.viewmodel

import android.os.Build.VERSION_CODES
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.iamspathan.bookmyshowclone.data.MovieRepository
import dev.iamspathan.bookmyshowclone.data.local.entity.MovieResponse
import dev.iamspathan.bookmyshowclone.util.NetworkHelper

class MainViewModel(val networkHelper: NetworkHelper, val movieRepository: MovieRepository) : ViewModel(){

    companion object {

        private const val API_KEY = "8ba84c25b76aa4754f8cc43203ca906a"

        private const val SOMETHING_WENT_WRONG = "Something went Wrong"
    }


    private val _movieResponse = MutableLiveData<MovieResponse>()
    val movieResponse : LiveData<MovieResponse>
    get() = _movieResponse


    private val _errorResponse = MutableLiveData<String>()
    val errorResponse : LiveData<String>
    get() = _errorResponse


    fun onCreate(){
        if(networkHelper.isNetworkConnected()){
            movieRepository.getMoviesRemote(API_KEY , { movieResponse ->
                _movieResponse.postValue(movieResponse)
            }, {
                error -> _errorResponse.postValue(error)
            })
        } else {

            movieRepository.getMoviesLocal { movieResponse ->
                if(movieResponse != null && movieResponse.list.isNotEmpty()) {
                    _movieResponse.postValue(movieResponse)
                } else {
                    _errorResponse.postValue(SOMETHING_WENT_WRONG)
                }
            }
        }
    }

}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(

    private val networkHelper: NetworkHelper,
    private val movieRepository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>):T {
        return MainViewModel(networkHelper,movieRepository) as T
    }
}
