package persistence


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.google.gson.Gson
import persistence.model.MovieModel
import persistence.model.MoviesResponse
import persistence.model.RawMoviesData

class Database constructor(
    private val moviesDao: MoviesDao
) : MoviesDataSource {

    override fun saveAllPopularMovies(popularMoviesJson: String) {
        moviesDao.insertPopularMovie(RawMoviesData(popularMoviesJson))
    }

    override fun getAllPopularMovies(): LiveData<List<MovieModel>> {
        return Transformations.switchMap(moviesDao.getAllPopularMovies()) { rawMoviesData ->
            val moviesResponse = Gson().fromJson<MoviesResponse>(rawMoviesData.responseJson, MoviesResponse::class.java)
            val moviesList = moviesResponse.results
            val mutableLiveData = MutableLiveData<List<MovieModel>>()
            mutableLiveData.value = moviesList
            mutableLiveData
        }
    }

}