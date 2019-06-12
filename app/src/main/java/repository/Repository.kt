package repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import api.network.MovieApiService
import persistence.MoviesDataSource
import persistence.model.MovieModel
import retrofit2.Response


class Repository(
    private val dataSource: MoviesDataSource,
    private val service: MovieApiService
) {

    fun fetchPopularMoviesFromServer(): LiveData<List<MovieModel>> {
        return Transformations.switchMap(dataSource.getAllPopularMovies()) { movies ->
            val movieLiveData = MutableLiveData<List<MovieModel>>()
            val movieList = movies.toMutableList()
            movieLiveData.value = movieList
            movieLiveData
        }
    }

    fun getAllPopularMovies(): Response<String> {
        return service.getAllPopularMovies(1 - 5)
    }
}