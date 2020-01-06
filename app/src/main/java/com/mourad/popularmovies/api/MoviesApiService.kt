package com.mourad.popularmovies.api

import com.google.gson.JsonObject
import com.mourad.popularmovies.R
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

    // The GET method needed to retrieve movies
    @GET("/3/discover/movie?api_key=89fff1029c9b643afad93c189f637124&language=en-USsort_by=popularity.desc")
    fun getMovies(@Query("year") year: String): Call<JsonObject>
}