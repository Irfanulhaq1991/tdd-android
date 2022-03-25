package com.example.tdd_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieListingViewModel(private val movieRepo: MoviesRepository) : ViewModel() {

    private val _movieListLiveData = MutableLiveData<UiState>()
    val movieListLiveData:LiveData<UiState> = _movieListLiveData

    fun fetchMoviesList() {
        processMoviesLisLoading()
    }


    private fun processMoviesLisLoading() {
        viewModelScope.launch {
            _movieListLiveData.value = UiState.ShowLoading
            _movieListLiveData.value = movieRepo.fetchMoviesList()
            _movieListLiveData.value = UiState.HideLoading
        }
    }




}
