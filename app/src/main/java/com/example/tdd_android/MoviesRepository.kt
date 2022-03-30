package com.example.tdd_android

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class MoviesRepository( private val inMemMoviesService: InMemMoviesService) {
    suspend fun fetchMoviesList(): UiState {
        return withContext(Dispatchers.IO) { // ship the execution off the main thread
            try {
                val result = inMemMoviesService.fetchMovies()
                if (result.isEmpty())
                    UiState.EmptyDataError
                else
                    UiState.Success(result)
            } catch (eIo: IOException) {
                UiState.OutOfInternetError
            }
        }
    }
}
