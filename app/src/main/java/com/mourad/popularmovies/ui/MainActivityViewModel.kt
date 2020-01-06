package com.mourad.popularmovies.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.mourad.popularmovies.model.Movie
import com.mourad.popularmovies.repository.MoviesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application): AndroidViewModel(application) {
    private val moviesRepository = MoviesRepository()

    val movies = MutableLiveData<List<Movie>>()
    val error = MutableLiveData<String>()

    fun getMovies(year: String) {
        moviesRepository.getMovies(year).enqueue(object: Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val results = response.body()?.get("results")
                val movies = GsonBuilder().create().fromJson(results, Array<Movie>::class.java).toList()

                movies.withIndex().forEach { (count) ->
                    movies[count].id = count + 1
                }

                if (response.isSuccessful) this@MainActivityViewModel.movies.value = movies
                else error.value = "An error occured: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                error.value = t.message
            }
        })
    }
}