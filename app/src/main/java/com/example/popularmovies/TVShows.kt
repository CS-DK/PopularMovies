package com.example.popularmovies

import com.google.gson.annotations.SerializedName

class TVShow {
    @SerializedName("original_name")
    var showName: String? = null

    @SerializedName("overview")
    var showDescription: String? = null

    @SerializedName("poster_path")
    var showImage: String? = null

    @SerializedName("popularity")
    var showPopularity: Double? = 0.0

    @SerializedName("first_air_date")
    var showDate: String? = null

    @SerializedName("vote_average")
    var showAvgVote: Float? = null
}
