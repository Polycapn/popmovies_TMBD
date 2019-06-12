package persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class MovieModel(
    @PrimaryKey
    val original_title: String,
    val overview: String,
    val poster_path: String?
)
//make MovieRawData the column of the table and responseJson is a row in that table
@Entity(tableName = "RawMoviesData")
data class RawMoviesData(
    @PrimaryKey
    val responseJson: String
)