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
import com.angelgomezsalazar.kotlinexample.retrofit.models.MovieResponse
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

        val movieList: MutableList<Movie> = arrayListOf()

        val linearLayoutManager = LinearLayoutManager(this)
        mainRecyclerView.layoutManager = linearLayoutManager

        val movieApi: MovieApi = retrofit.create(MovieApi::class.java)

        callGenreApi(movieList, movieApi)

    }

    fun callGenreApi(movieList: MutableList<Movie>, movieApi: MovieApi) {
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

                callUpcomingMovieApi(movieList, movieApi, movieRecyclerAdapter, 1)
            }

            override fun onFailure(call: Call<GenreResponse>?, t: Throwable?) {
            }
        })
    }

    fun callUpcomingMovieApi(movieList: MutableList<Movie>, movieApi: MovieApi,
                             movieRecyclerAdapter: MovieRecyclerAdapter, page: Int) {
        val upcomingMovieCall: Call<MovieResponse> = movieApi.getUpcomingMovies(page, Api.KEY)

        upcomingMovieCall.enqueue(object: Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>?,
                                    response: Response<MovieResponse>?) {
                movieList.addAll(response!!.body().results!!)
                movieRecyclerAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
            }
        })
    }

}
