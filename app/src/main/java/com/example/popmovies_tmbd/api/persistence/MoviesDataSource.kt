package com.example.popmovies_tmbd.api.persistence

import androidx.lifecycle.LiveData
import com.example.popmovies_tmbd.api.persistence.model.MovieModel

interface MoviesDataSource {
    fun getAllPopularMovies(): LiveData<List<MovieModel>>
    fun saveAllPopularMovies(popularMoviesJson: String): String
}
