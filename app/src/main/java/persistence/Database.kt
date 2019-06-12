package persistence


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.google.gson.Gson
import persistence.model.MovieModel
import persistence.model.MoviesResponse
import javax.inject.Inject

class Database @Inject constructor(
    private val moviesDao: MoviesDao
) : MoviesDataSource {

    override fun saveAllPopularMovies(popularMoviesJson: String) {
        moviesDao.insertPopularMovie(popularMoviesJson)
    }

    override fun getAllPopularMovies(): LiveData<List<MovieModel>> {
        return Transformations.switchMap(moviesDao.getAllPopularMovies()) { responseJson ->
            val moviesResponse = Gson().fromJson<MoviesResponse>(responseJson, MoviesResponse::class.java)
            val moviesList = moviesResponse.results
            val mutableLiveData = MutableLiveData<List<MovieModel>>()
            mutableLiveData.value = moviesList
            mutableLiveData
        }
    }

}