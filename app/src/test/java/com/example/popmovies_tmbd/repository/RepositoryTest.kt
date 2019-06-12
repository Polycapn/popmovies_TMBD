package com.example.popmovies_tmbd.repository

import api.network.MovieApiService
import io.mockk.mockk
import org.junit.Test
import persistence.MoviesDataSource
import retrofit2.Response

class RepositoryTest {
    val dataSource = mockk<MoviesDataSource>(relaxed = true)
    val movieApiService = mockk<MovieApiService>(relaxed = true)


    @Test
    fun fetchPopularMovies(): Response<String> {
        return movieApiService.getAllPopularMovies(1 )
    }
    @Test
    fun saveMoviesToDB(movieJson: String) {
        return dataSource.saveAllPopularMovies(movieJson)
    }


}