    package com.example.myapplication.data

    data class ExerciseUiState(
        val selectedModeIndex: Int = 0,
        val weight: Int = 40,
        val sets: Int = 8,
        val reps: Int = 5,
        val restSeconds: Int = 60,
        val detailLevel: Int = 1,
        val safetyAssist: Int = 0,
        val currentRep: Int = 0,
        val currentSet: Int = 1,
        val isResting: Boolean = false,
        val restTimeLeft: Int = 0
    )
