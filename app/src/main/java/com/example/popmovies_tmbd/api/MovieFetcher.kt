package com.example.popmovies_tmbd.api

import com.example.popmovies_tmbd.api.network.MovieApiService
import com.example.popmovies_tmbd.api.persistence.MoviesDataSource

class MovieFetcher(
    private val apiService: MovieApiService,
    private val database: MoviesDataSource
) : FetcherUseCase {

    override fun fetchPopularMovies() {
        val response = apiService.fetchPopularMovies()
        if (response.isSuccessful) {
            response.body()?.let {
                saveMoviesToDB(it)
            }
        }
    }

    fun saveMoviesToDB(json: String) {
        database.saveAllPopularMovies(json)
    }
}

