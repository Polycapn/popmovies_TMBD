package persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Movies")
data class PopularMovies(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterUrlPath: String
)
