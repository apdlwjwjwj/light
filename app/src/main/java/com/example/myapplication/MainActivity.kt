package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.screen.ExerciseScreen
import com.example.myapplication.viewmodel.ExerciseViewModel
import com.example.myapplication.viewmodel.SplashViewModel
import com.example.myapplication.viewmodel.PasswordViewModel
import com.example.myapplication.ui.screen.SplashScreen
import com.example.myapplication.ui.screen.PasswordInputScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.myapplication.ui.screen.ExercisingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val splashViewModel: SplashViewModel = viewModel()
                val passwordViewModel: PasswordViewModel = viewModel()
                val exerciseViewModel: ExerciseViewModel = viewModel()
                val showSplash by splashViewModel.showSplash

                var memberName by remember { mutableStateOf<String?>(null) }
                var isWorkoutStarted by remember { mutableStateOf(false) }

                if (showSplash) {
                    SplashScreen()
                } else if (memberName == null) {
                    PasswordInputScreen(
                        onSuccess = { name -> memberName = name },
                        viewModel = passwordViewModel
                    )
                } else if (!isWorkoutStarted) {
                    ExerciseScreen(
                        userName = memberName!!,
                        viewModel = exerciseViewModel,
                        onLogout = { memberName = null },
                        onStartWorkout = { isWorkoutStarted = true }
                    )
                } else {
                   ExercisingScreen(
                        userName = memberName!!,
                        state = exerciseViewModel.uiState,
                        modes = exerciseViewModel.modes,
                        onBack = { isWorkoutStarted = false },
                        onLogout = {
                            memberName = null
                            isWorkoutStarted = false
                        },
                       onWeightChange = { newWeight ->
                           exerciseViewModel.setWeight(newWeight)
                       }
                    )
                }
            }
        }

    }
}
