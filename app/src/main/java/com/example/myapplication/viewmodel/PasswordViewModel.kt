package com.example.myapplication.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class PasswordViewModel : ViewModel() {
    private val _password = mutableStateOf("")
    val password: State<String> get() = _password

    private val _isValid = mutableStateOf<Boolean?>(null)
    val isValid: State<Boolean?> get() = _isValid

    fun appendDigit(digit: String) {
        if (_password.value.length < 4) {
            _password.value += digit
            if (_password.value.length == 4) {
                _isValid.value = true
            }
        }
    }

    fun clear() {
        _password.value = ""
        _isValid.value = null
    }
}
