package com.example.tdd_android

sealed class UiState {
    object ShowLoading:UiState()
    object HideLoading : UiState()
    object EmptyDataError:UiState()
    object OutOfInternetError : UiState()

    data class Success(val data:List<Movie>? = null) : UiState()
}
