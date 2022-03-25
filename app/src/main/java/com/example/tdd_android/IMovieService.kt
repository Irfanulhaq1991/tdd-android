package com.example.tdd_android

interface IMovieService {
   suspend fun fetchMovies(): List<Movie>
}