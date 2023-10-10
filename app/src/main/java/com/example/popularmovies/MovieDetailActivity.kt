package com.example.popularmovies

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)

        // Get movie details from intent extras
        val movieName = intent.getStringExtra("movie_name")
        val movieDescription = intent.getStringExtra("movie_description")
        val movieImage = intent.getStringExtra("movie_image")
        val moviePopularity = intent.getStringExtra("movie_popularity")
        val movieDate = intent.getStringExtra("movie_release_date")
        val movieAvgVote = intent.getStringExtra("movie_average_vote")

        val movieNameTextView = findViewById<TextView>(R.id.movie_title)
        val movieDescriptionTextView = findViewById<TextView>(R.id.movie_desc)
        val movieImageView = findViewById<ImageView>(R.id.movie_img)
        val moviePopularityTextView = findViewById<TextView>(R.id.movie_popularity)
        val movieDateTextView = findViewById<TextView>(R.id.movie_date)
        val movieVoteTextView = findViewById<TextView>(R.id.movie_vote)

        // Set movie details in the layout
        movieNameTextView.text = movieName
        movieDescriptionTextView.text = movieDescription
        moviePopularityTextView.text = "Popularity: $moviePopularity"
        movieDateTextView.text = "Release Date: $movieDate"
        movieVoteTextView.text = "Average Vote: $movieAvgVote"

        val baseUrl = "https://image.tmdb.org/t/p/w500/"
        val imageUrl = baseUrl + movieImage

        Glide.with(this).load(imageUrl).centerCrop().transform(RoundedCorners(50))
            .into(movieImageView)
    }
}
