package com.example.popularmovies

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


class MovieRecyclerViewAdapter(
    private val movies: List<Movie>,
    private val mListener: OnListFragmentInteractionListener?,
    private val context: Context
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null
        val mMovieTitle: TextView = mView.findViewById<View>(R.id.movie_name) as TextView
        val mMovieDescription: TextView = mView.findViewById<View>(R.id.movie_description) as TextView
        val mMovieImage: ImageView = mView.findViewById<View>(R.id.movie_image) as ImageView
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.movieName
        holder.mMovieDescription.text = movie.movieDescription

        val baseUrl = "https://image.tmdb.org/t/p/w500/"
        val imageUrl = baseUrl + movie.movieImage
        Glide.with(holder.mView).load(imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground).centerInside().centerCrop().transform(RoundedCorners(30)).into(holder.mMovieImage)

        holder.mView.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("movie_name", movie.movieName)
            intent.putExtra("movie_description", movie.movieDescription)
            intent.putExtra("movie_image", movie.movieImage)
            intent.putExtra("movie_release_date", movie.movieDate)
            intent.putExtra("movie_popularity", movie.moviePopularity?.toInt().toString())
            intent.putExtra("movie_average_vote", movie.movieAvgVote.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}