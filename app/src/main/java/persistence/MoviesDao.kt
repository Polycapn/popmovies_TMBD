package persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import persistence.model.MovieModel

@Dao
interface MoviesDao {
    @Query("SELECT * FROM Movies")
    fun getAllPopularMovies(): LiveData<List<MovieModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovie(movie: MovieModel)
}