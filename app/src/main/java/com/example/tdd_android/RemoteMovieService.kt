package com.example.tdd_android

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.concurrent.TimeoutException



class RemoteMovieService(private val api: RemoteMovieApi) : IMovieService {
    override suspend fun fetchMovies(): List<Movie> {
        return try {
            val apiResponse = api.getMovies()
            if (!apiResponse.isSuccessful) {
                throw OutOfInternetException()
            }

            val parsedData = parsAndGetBody(apiResponse)
           if(parsedData.isEmpty()){
               throw NoDataException()
           }
            parsedData
        } catch (e: TimeoutException) {
            throw OutOfInternetException()

        }
    }

    private fun parsAndGetBody(response: Response<ResponseBody>): List<Movie> {
        val typToken = object : TypeToken<List<Movie>>() {}.type
        val gson = Gson()
        return gson.fromJson(response.body()!!.string(), typToken)

    }
}