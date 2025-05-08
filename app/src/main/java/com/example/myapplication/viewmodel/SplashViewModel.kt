package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class SplashViewModel : ViewModel() {
    private val _showSplash = mutableStateOf(true)
    val showSplash: State<Boolean> get() = _showSplash

    init {
        viewModelScope.launch {
            delay(1500)
            _showSplash.value = false
        }
    }
}
