package dev.iamspathan.bookmyshowclone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import dev.iamspathan.bookmyshowclone.R.layout
import dev.iamspathan.bookmyshowclone.data.MovieRepoImpl
import dev.iamspathan.bookmyshowclone.data.local.MovieDatabase
import dev.iamspathan.bookmyshowclone.data.local.entity.Movie
import dev.iamspathan.bookmyshowclone.data.remote.RetrofitBuilder
import dev.iamspathan.bookmyshowclone.ui.adapter.MovieAdapter
import dev.iamspathan.bookmyshowclone.ui.viewmodel.MainViewModel
import dev.iamspathan.bookmyshowclone.ui.viewmodel.MainViewModelFactory
import dev.iamspathan.bookmyshowclone.util.NetworkHelper
import kotlinx.android.synthetic.main.activity_main.errorView
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
    }


    fun setupViewModel() {
        showProgress()
        viewModel = ViewModelProvider(this,
        MainViewModelFactory(
            NetworkHelper(this), MovieRepoImpl(
                MovieDatabase.getInstance(this).movieDao(),
                RetrofitBuilder.buildService()
            )
        ))[MainViewModel::class.java]
        viewModel.onCreate()
    }

    private fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress(){
        progressBar.visibility = View.GONE
    }

    private fun observeViewModel(){
        viewModel.movieResponse.observe(this,
            Observer {
                showMovies(it.list)
            }
        )

        viewModel.errorResponse.observe(this,
            Observer {
            showErrorMessage(it)
                hideProgress()
        })
    }

    private fun showErrorMessage(message: String?) {
        errorView.visibility = View.VISIBLE
        errorView.text = message
    }

    private fun showMovies(list: List<Movie>) {
        recyclerView.visibility = View.VISIBLE
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = MovieAdapter(movies = list)
    }



}