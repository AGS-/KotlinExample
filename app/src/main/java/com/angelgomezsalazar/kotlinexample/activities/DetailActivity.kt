package com.angelgomezsalazar.kotlinexample.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.angelgomezsalazar.kotlinexample.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by angelgomez on 8/18/16.
 */
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailIntent = intent
        val name = detailIntent.getStringExtra(NAME_EXTRA)
        val image = detailIntent.getStringExtra(IMAGE_EXTRA)
        val genre = detailIntent.getStringExtra(GENRE_EXTRA)
        val release = detailIntent.getStringExtra(RELEASE_EXTRA)
        val overview = detailIntent.getStringExtra(OVERVIEW_EXTRA)

        Picasso.with(this).load("http://image.tmdb.org/t/p/w500" + image)
                .placeholder(R.drawable.sample_movie_poster)
                .into(activityDetailImagePoster)

        activityDetailTextMovieName.text = name
        activityDetailTextGenre.text = genre
        activityDetailTextReleaseDate.text = release
        activityDetailTextOverview.text = overview
    }

    companion object {

        val NAME_EXTRA = "name_extra"
        val IMAGE_EXTRA = "image_extra"
        val GENRE_EXTRA = "genre_extra"
        val RELEASE_EXTRA = "release_extra"
        val OVERVIEW_EXTRA = "overview_extra"
    }
}
