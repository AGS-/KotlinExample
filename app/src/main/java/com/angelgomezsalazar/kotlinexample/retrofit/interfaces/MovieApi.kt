package com.angelgomezsalazar.kotlinexample.retrofit.interfaces

import com.angelgomezsalazar.kotlinexample.retrofit.models.GenreResponse
import com.angelgomezsalazar.kotlinexample.retrofit.models.MovieResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by angelgomez on 8/18/16.
 */
interface MovieApi {

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("page") page: Int, @Query("api_key") apiKey: String):
            Call<MovieResponse>

    @GET("genre/movie/list")
    fun getGenreList(@Query("api_key") apiKey: String): Call<GenreResponse>
}
