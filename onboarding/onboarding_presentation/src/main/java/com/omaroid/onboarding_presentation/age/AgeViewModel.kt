package com.omaroid.onboarding_presentation.age

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaroid.core.domain.preferences.Preferences
import com.omaroid.core.domain.use_cases.FilterOutDigits
import com.omaroid.core.util.UiEvent
import com.omaroid.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val filterOutDigits: FilterOutDigits,
    private val preferences: Preferences
) : ViewModel() {

    var age by mutableStateOf("20")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAgeEnter(age: String) {
        if (age.length <= 2) {
            this.age = filterOutDigits(age)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val ageNumber = age.toIntOrNull() ?: kotlin.run {
                Log.i("AgeViewModel", "Age is $age")
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        UiText.StringResources(com.omaroid.core.R.string.error_age_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveAge(ageNumber)
            _uiEvent.send(UiEvent.Success)
        }
    }
}