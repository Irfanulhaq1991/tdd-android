package com.example.tdd_android

import kotlinx.coroutines.delay

class InMemMoviesService(private val data: List<Movie>?) : IMovieService {
      override suspend fun fetchMovies(): List<Movie> {
        if (data == null) {
            throw OutOfInternetException()
        } else if (data.isEmpty())
            throw NoDataException()

        return data
    }

}
