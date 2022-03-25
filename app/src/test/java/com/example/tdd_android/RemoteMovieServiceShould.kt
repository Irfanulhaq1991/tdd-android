package com.example.tdd_android

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.util.concurrent.TimeoutException

class RemoteMovieServiceShould : ServiceContractTests() {
    override fun getServiceWith(list: MutableList<Movie>): IMovieService {
        return RemoteMovieService(MovieApiWithData(list))
    }

    override fun getOfflineService(): IMovieService {
        return RemoteMovieService(OfflineMovieApi())
    }


}

class MovieApiWithData(private val data: List<Movie>) : RemoteMovieApi {
    override suspend fun getMovies(): Response<ResponseBody> {
        return Response.success(createResponseBody())
    }

    private fun createResponseBody(): ResponseBody {
        val typToken = object : TypeToken<List<Movie>>() {}.type
        val jsonData = Gson().toJson(data, typToken)
        val contentType = "application/json; charset=utf-8".toMediaType()
        return jsonData.toResponseBody(contentType)
    }


}

class OfflineMovieApi() : RemoteMovieApi {
    override suspend fun getMovies(): Response<ResponseBody> {
        throw  TimeoutException()
    }


}
