package persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import persistence.model.PopularMovies

@Database(entities = [PopularMovies::class], version = 1)
abstract class MoviesRoomDB : RoomDatabase(){

}