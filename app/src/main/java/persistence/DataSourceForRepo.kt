package persistence

import androidx.lifecycle.LiveData
import persistence.model.MovieModel
import persistence.model.PopularMovies

interface DataSourceForRepo {

    fun getAllPopularMovies(): LiveData<List<PopularMovies>>
    fun saveAllPopularMovies(popularMovies: List<MovieModel>)

}
