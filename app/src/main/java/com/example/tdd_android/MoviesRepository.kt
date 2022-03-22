package com.example.tdd_android

class MoviesRepository(private val inMemMoviesService: InMemMoviesService) {
    fun fetchMoviesList():UiState {
       return UiState.Success(inMemMoviesService.fetchMovies())
    }

}
