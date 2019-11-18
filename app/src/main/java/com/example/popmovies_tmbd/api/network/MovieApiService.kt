package com.example.popmovies_tmbd.api.network

import com.example.popmovies_tmbd.BuildConfig
import retrofit2.Response
import retrofit2.http.GET

interface
MovieApiService {
    @GET("3/movie/popular?api_key=${BuildConfig.API_KEY}")
    fun fetchPopularMovies(): Response<String>
}