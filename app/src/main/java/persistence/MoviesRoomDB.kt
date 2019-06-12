package persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import persistence.model.MovieModel
import persistence.model.MoviesResponse
import persistence.model.RawMoviesData

@Database(entities = [RawMoviesData::class], version = 1, exportSchema = false)
abstract class MoviesRoomDB : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}
