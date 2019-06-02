package persistence

import androidx.lifecycle.LiveData
import persistence.model.MovieModel
import persistence.model.PopularMovies
import javax.inject.Inject

class Database @Inject constructor(
    private val moviesDao: MoviesDao
) : DataSourceForRepo {

    override fun saveAllPopularMovies(popularMovies: List<MovieModel>) {
        popularMovies.forEach {
            moviesDao.insertAllPopularMovies(it)
        }
    }

    override fun getAllPopularMovies(): LiveData<List<PopularMovies>> {
        return moviesDao.getAllPopularMovies()
    }

}