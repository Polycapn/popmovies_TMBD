package persistence

import androidx.lifecycle.LiveData
import persistence.model.MovieModel

interface MoviesDataSource {
    fun getAllPopularMovies(): LiveData<List<MovieModel>>
    fun saveAllPopularMovies(popularMoviesJson: String)
}
