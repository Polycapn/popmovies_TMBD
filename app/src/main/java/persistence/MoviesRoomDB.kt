package persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import persistence.model.MovieModel

@Database(entities = [MovieModel::class], version = 1)
abstract class MoviesRoomDB : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}
