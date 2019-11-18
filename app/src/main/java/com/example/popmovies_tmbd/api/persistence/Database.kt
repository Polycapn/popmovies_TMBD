package com.example.popmovies_tmbd.api.persistence


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.popmovies_tmbd.api.MovieFetcher
import com.example.popmovies_tmbd.api.persistence.model.MovieModel
import com.example.popmovies_tmbd.api.persistence.model.MoviesResponse
import com.example.popmovies_tmbd.api.persistence.model.RawMoviesData
import com.google.gson.Gson

class Database constructor(
    private val moviesDao: MoviesDao,
    private val movieFetcher: MovieFetcher
) : MoviesDataSource {

    override fun saveAllPopularMovies(popularMoviesJson: String): String {
        return  moviesDao.insertPopularMovie(RawMoviesData(movieFetcher.saveMoviesToDB(popularMoviesJson).toString())).toString()
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