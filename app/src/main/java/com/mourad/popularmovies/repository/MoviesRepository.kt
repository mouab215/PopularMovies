package com.mourad.popularmovies.repository

import com.mourad.popularmovies.api.MoviesApi
import com.mourad.popularmovies.api.MoviesApiService

class MoviesRepository {
    private val moviesApi: MoviesApiService = MoviesApi.createApi()

    fun getMovies(year: String) = moviesApi.getMovies(year)
}