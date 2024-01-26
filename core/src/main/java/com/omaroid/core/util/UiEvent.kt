package com.omaroid.core.util

sealed class UiEvent {
    data object Success : UiEvent()
    data class Navigate(val route: String): UiEvent()
    data object NavigateUp: UiEvent()
    data class ShowSnackBar(val message: UiText) : UiEvent()
}