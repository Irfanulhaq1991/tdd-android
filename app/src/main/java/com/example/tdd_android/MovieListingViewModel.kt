package com.example.tdd_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieListingViewModel(private val movieRepo: MoviesRepository) : ViewModel() {

    private val _movieListLivieData = MutableLiveData<UiState>()
    val movieListLiveData:LiveData<UiState> = MutableLiveData<UiState>()

    fun fetchMoviesList() {
        movieRepo.fetchMoviesList()
    }


}
