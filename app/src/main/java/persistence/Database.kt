package persistence

import androidx.lifecycle.LiveData
import persistence.model.MovieModel
import javax.inject.Inject

class Database @Inject constructor(
    private val moviesDao: MoviesDao
) : MoviesDataSource {

    override fun saveAllPopularMovies(popularMovies: List<MovieModel>) {
        popularMovies.forEach {
            moviesDao.insertPopularMovie(it)
        }
    }

    override fun getAllPopularMovies(): LiveData<List<MovieModel>> {
        return moviesDao.getAllPopularMovies()
    }

}