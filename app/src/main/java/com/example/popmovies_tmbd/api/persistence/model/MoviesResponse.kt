package com.example.popmovies_tmbd.api.persistence.model

data class MoviesResponse(
    val page: Int,
    var results: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)