package com.example.tdd_android

sealed class UiState {
    object ShowLoading:UiState()
    object HideLoading : UiState()
    object EmptyDataError:UiState()
    object OutOfinternetError : UiState()

    data class Success(val data:Any? = null) : UiState()
}
