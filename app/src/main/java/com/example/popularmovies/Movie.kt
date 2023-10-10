package com.example.popularmovies

import com.google.gson.annotations.SerializedName

class Movie {
    @SerializedName("original_title")
    var movieName: String? = null

    @JvmField
    @SerializedName("overview")
    var movieDescription: String? = null

    @JvmField
    @SerializedName("poster_path")
    var movieImage: String? = null

    @JvmField
    @SerializedName("popularity")
    var moviePopularity: Double? = 0.0

    @JvmField
    @SerializedName("release_date")
    var movieDate: String? = null

    @JvmField
    @SerializedName("vote_average")
    var movieAvgVote: Float? = null
}