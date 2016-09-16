package com.angelgomezsalazar.kotlinexample.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.angelgomezsalazar.kotlinexample.R
import com.angelgomezsalazar.kotlinexample.adapters.MovieRecyclerAdapter
import com.angelgomezsalazar.kotlinexample.retrofit.models.Movie

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val genreIds: MutableList<Int> = arrayListOf()
        val movie1 = Movie()
        movie1.title = "Hello"
        movie1.posterPath = "/dQ7uxvsVTspVIsqjfgQj8usJpwX.jpg"
        movie1.genreIds = genreIds
        movie1.release_date = "RELEASE"
        movie1.overview = "Hello Overview World"

        val movieList: MutableList<Movie> = arrayListOf()
        val genreHashMap : HashMap<Int, String> = hashMapOf()

        movieList.add(movie1)

        val linearLayoutManager = LinearLayoutManager(this)
        val movieRecyclerAdapter = MovieRecyclerAdapter(movieList, genreHashMap)
        mainRecyclerView.layoutManager = linearLayoutManager
        mainRecyclerView.adapter = movieRecyclerAdapter

    }
}
