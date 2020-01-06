package com.mourad.popularmovies.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mourad.popularmovies.R
import com.mourad.popularmovies.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movies = arrayListOf<Movie>()
    private val movieAdapter = MainAdapter(movies) { movie -> onMovieClick(movie) }

    companion object{
        const val MOVIE = "MOVIE"
    }

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initViewModels()
    }

    private fun initViews() {
        btnSubmit.setOnClickListener {
            val year = etYear.text.toString()

            if (year.toInt() in 1990..2020) {
                viewModel.getMovies(year)
            } else {
                Snackbar.make(rvMovies, "Please put in a release date in-between 1990 and 2020", Snackbar.LENGTH_LONG).show()
            }
        }
        rvMovies.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvMovies.adapter = movieAdapter
    }

    private fun initViewModels() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.movies.observe(this, Observer {
            movies.clear()
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

    }

    private fun onMovieClick(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(MOVIE, movie)
        startActivity(intent)
    }
}
