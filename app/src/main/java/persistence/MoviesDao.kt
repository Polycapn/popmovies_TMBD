package persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {
    @Query("SELECT * FROM Movies")
    fun getAllPopularMovies(): LiveData<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovie(moviesJson: String)
}