package api

import api.network.MovieApiService
import persistence.MoviesDataSource
import retrofit2.Response

class MovieFetcher(
    private val movieApiService: MovieApiService,
    private val dataSource: MoviesDataSource
) {

    private fun fetchPopularMovies(): Response<String> {
        return movieApiService.getAllPopularMovies(1)
    }

    private fun saveMoviesToDB(movieJson: String) {
        return dataSource.saveAllPopularMovies(movieJson)
    }
}

