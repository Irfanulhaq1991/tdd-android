package com.example.tdd_android

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface RemoteMovieApi {

   @GET("/movies")
    suspend fun getMovies(): Response<ResponseBody>
}
