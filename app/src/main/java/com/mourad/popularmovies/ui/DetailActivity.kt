package com.mourad.popularmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.mourad.popularmovies.R
import com.mourad.popularmovies.model.Movie
import com.mourad.popularmovies.ui.MainActivity.Companion.MOVIE
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        movie = intent.getParcelableExtra(MOVIE)!!

        initViews()
    }

    private fun initViews() {
        //Enable back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Set the texts
        tvOverview.text = movie.overview
        tvRating.text = movie.rating.toString()
        tvRelease.text = movie.releaseDate
        tvTitle.text = movie.title

        //Load the poster and backdrop
        Glide.with(this).load(movie.getBackdropImageUrl()).into(ivBackdrop)
        Glide.with(this).load(movie.getPosterImageUrl()).into(ivPoster)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
