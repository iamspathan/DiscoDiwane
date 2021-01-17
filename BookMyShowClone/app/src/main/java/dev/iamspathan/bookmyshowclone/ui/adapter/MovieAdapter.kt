package dev.iamspathan.bookmyshowclone.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.iamspathan.bookmyshowclone.R
import dev.iamspathan.bookmyshowclone.data.local.entity.Movie
import kotlinx.android.synthetic.main.movie_item_layout.view.averageVoting
import kotlinx.android.synthetic.main.movie_item_layout.view.moviePoster
import kotlinx.android.synthetic.main.movie_item_layout.view.movieTitle
import kotlinx.android.synthetic.main.movie_item_layout.view.releaseDate
import kotlinx.android.synthetic.main.movie_item_layout.view.totalVotes

class MovieAdapter(private val movies:List<Movie>):
    RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(
           R.layout.movie_item_layout, parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.count()
}

class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    fun bind(movie: Movie){
        Glide.with(itemView.context).load(IMAGE_BASE_URL + movie.posterPath)
            .into(itemView.moviePoster)

        with(itemView){
            movieTitle.text = movie.title
            releaseDate.text = movie.releaseDate
            averageVoting.text = movie.voteAverage.toString()
            totalVotes.text = movie.voteCount.toString()
        }

    }


}