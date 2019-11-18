package com.example.popmovies_tmbd.api.network

import com.example.popmovies_tmbd.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class ApiServiceHelper {

    fun getOkHttp(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val body = HttpLoggingInterceptor.Level.BODY
        httpLoggingInterceptor.level = body

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(httpLoggingInterceptor)
        return builder.build()
    }


    fun buildService(): MovieApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(getOkHttp())
            .build()
        return retrofit.create(MovieApiService::class.java)

    }

}

