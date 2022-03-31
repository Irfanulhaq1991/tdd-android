package com.example.tdd_android

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface RemoteMovieApi {

   @GET("/v1/cbb83723-30d0-4b56-899d-519e1bbc9c29")
    suspend fun getMovies(): Response<ResponseBody>
}
