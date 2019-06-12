package api.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface
MovieApiService {

    @GET("/3/discover/movie?language=en&sort_by=popularity.desc")
    fun getAllPopularMovies(@Query("page") page: Int): Response<String>
}