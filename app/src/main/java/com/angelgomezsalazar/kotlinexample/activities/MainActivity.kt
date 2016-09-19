package com.angelgomezsalazar.kotlinexample.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.angelgomezsalazar.kotlinexample.R
import com.angelgomezsalazar.kotlinexample.adapters.MovieRecyclerAdapter
import com.angelgomezsalazar.kotlinexample.retrofit.interfaces.MovieApi
import com.angelgomezsalazar.kotlinexample.retrofit.models.Genre
import com.angelgomezsalazar.kotlinexample.retrofit.models.GenreResponse
import com.angelgomezsalazar.kotlinexample.retrofit.models.Movie
import com.angelgomezsalazar.kotlinexample.utils.Api
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val genreIds: MutableList<Int> = arrayListOf()
        val movie1 = Movie()
        movie1.title = "Hello"
        movie1.posterPath = "/dQ7uxvsVTspVIsqjfgQj8usJpwX.jpg"
        movie1.genreIds = genreIds
        movie1.release_date = "RELEASE"
        movie1.overview = "Hello Overview World"

        val movieList: MutableList<Movie> = arrayListOf()

        movieList.add(movie1)

        val linearLayoutManager = LinearLayoutManager(this)
        mainRecyclerView.layoutManager = linearLayoutManager

        callGenreApi(retrofit, movieList)

    }

    fun callGenreApi(retrofit: Retrofit, movieList: MutableList<Movie>) {
        val movieApi: MovieApi = retrofit.create(MovieApi::class.java)
        val genreCall: Call<GenreResponse> = movieApi.getGenreList(Api.KEY)

        genreCall.enqueue(object: Callback<GenreResponse> {
            override fun onResponse(call: Call<GenreResponse>?,
                                    response: Response<GenreResponse>?) {
                val genreHashMap : MutableMap<Int, String?> = mutableMapOf()
                for (genre: Genre in response!!.body().genres!!) {
                    genreHashMap.put(genre.id, genre.name)
                }

                val movieRecyclerAdapter = MovieRecyclerAdapter(movieList, genreHashMap)
                mainRecyclerView.adapter = movieRecyclerAdapter
            }

            override fun onFailure(call: Call<GenreResponse>?, t: Throwable?) {
            }
        })
    }

}
