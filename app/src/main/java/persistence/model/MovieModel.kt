package persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Movies")
data class MovieModel(
    @PrimaryKey
    val original_title: String,
    val overview: String,
    val poster_path: String?
)
