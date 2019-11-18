package com.example.popmovies_tmbd.api.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.popmovies_tmbd.api.persistence.model.RawMoviesData

@Database(entities = [RawMoviesData::class], version = 1, exportSchema = false)
abstract class MoviesRoomDB : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}
