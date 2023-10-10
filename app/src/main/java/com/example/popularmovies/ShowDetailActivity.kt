package com.example.popularmovies

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class ShowDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_detail)

        // Get show details from intent extras
        val showName = intent.getStringExtra("show_name")
        val showDescription = intent.getStringExtra("show_description")
        val showImage = intent.getStringExtra("show_image")
        val showPopularity = intent.getDoubleExtra("show_popularity", 0.0)
        val showDate = intent.getStringExtra("show_release_date")
        val showAvgVote = intent.getFloatExtra("show_average_vote", 0.0f)

        val showNameTextView = findViewById<TextView>(R.id.show_title)
        val showDescriptionTextView = findViewById<TextView>(R.id.show_desc)
        val showImageView = findViewById<ImageView>(R.id.show_img)
        val showPopularityTextView = findViewById<TextView>(R.id.show_popularity)
        val showDateTextView = findViewById<TextView>(R.id.show_date)
        val showVoteTextView = findViewById<TextView>(R.id.show_vote)

        // Set show details in the layout
        showNameTextView.text = showName
        showDescriptionTextView.text = showDescription
        showPopularityTextView.text = "Popularity: ${showPopularity.toInt()}"
        showDateTextView.text = "Release Date: $showDate"
        showVoteTextView.text = "Average Vote: $showAvgVote"

        val baseUrl = "https://image.tmdb.org/t/p/w500/"
        val imageUrl = baseUrl + showImage

        Glide.with(this).load(imageUrl).centerCrop().transform(RoundedCorners(50))
            .into(showImageView)
    }
}
