package com.angelgomezsalazar.kotlinexample.retrofit.models

import com.google.gson.annotations.SerializedName

/**
 * Created by angelgomez on 8/18/16.
 */
class Movie {

    var title: String? = null
    @SerializedName("poster_path")
    var posterPath: String? = null
    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null
    var release_date: String? = null
    var overview: String? = null
}
