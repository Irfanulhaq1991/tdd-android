package com.example.tdd_android

sealed class UiState {
    object ShowLoading:UiState()
    object HideLoading : UiState()
    object EmptyDataError:UiState()

    data class Success(val data:Any? = null) : UiState()
}
