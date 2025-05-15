package com.example.myapplication.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.ExerciseUiState

class ExerciseViewModel : ViewModel() {
    val modes = listOf("일반", "네거티브", "포지티브", "밴드", "체인", "드롭", "워터", "등속성")

    var uiState by mutableStateOf(ExerciseUiState())
        private set

    fun selectMode(index: Int) {
        uiState = uiState.copy(selectedModeIndex = index, detailLevel = 1)
    }
    fun setDetailLevel(level: Int) {
        uiState = uiState.copy(detailLevel = level)
    }
    fun changeWeight(delta: Int) {
        uiState = uiState.copy(weight = (uiState.weight + delta).coerceIn(0, 100))
    }
    fun changeSets(delta: Int) {
        uiState = uiState.copy(sets = (uiState.sets + delta).coerceIn(1, 20))
    }
    fun changeReps(delta: Int) {
        uiState = uiState.copy(reps = (uiState.reps + delta).coerceIn(1, 20))
    }
    fun changeRest(delta: Int) {
        uiState = uiState.copy(restSeconds = (uiState.restSeconds + delta).coerceIn(0, 3600))
    }
    fun resetState() {
        uiState = ExerciseUiState()
    }
    fun setSafetyAssist(idx: Int) {
        uiState = uiState.copy(safetyAssist = idx)
    }
    fun setWeight(newWeight: Int) {
        uiState = uiState.copy(weight = newWeight.coerceIn(0, 100)) // 0~100kg 제한
    }
}
