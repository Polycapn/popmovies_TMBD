package com.example.popmovies_tmbd.api.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.popmovies_tmbd.api.FetcherUseCase
import com.example.popmovies_tmbd.api.persistence.MoviesDataSource
import com.example.popmovies_tmbd.api.persistence.model.MovieModel


class Repository(
    private val dataSource: MoviesDataSource,
    private val fetcherUseCase: FetcherUseCase
) {

    private fun fetchPopularMovies() {
        fetcherUseCase.fetchPopularMovies()
    }


    fun getAllPopularMovies(): LiveData<List<MovieModel>> {
        return Transformations.switchMap(dataSource.getAllPopularMovies()) { movies ->
            val movieLiveData = MutableLiveData<List<MovieModel>>()
            if (movies.isNullOrEmpty()) {
                fetchPopularMovies()
            }
            movieLiveData.value = movies
            movieLiveData
        }
    }

}
