package com.example.popmovies_tmbd.api.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.popmovies_tmbd.api.persistence.model.RawMoviesData

@Dao
interface MoviesDao {
    @Query("SELECT * FROM RawMoviesData")
    fun getAllPopularMovies(): LiveData<RawMoviesData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovie(moviesData: RawMoviesData)
}