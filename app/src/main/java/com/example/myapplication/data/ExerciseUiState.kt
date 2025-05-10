    package com.example.myapplication.data

    data class ExerciseUiState(
        val selectedModeIndex: Int = 0,
        val weight: Int = 40,
        val sets: Int = 8,
        val reps: Int = 5,
        val restSeconds: Int = 60
    )
