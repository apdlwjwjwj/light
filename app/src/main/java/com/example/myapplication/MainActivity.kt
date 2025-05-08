package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.screen.SplashScreen
import com.example.myapplication.viewmodel.SplashViewModel
import com.example.myapplication.viewmodel.PasswordViewModel
import com.example.myapplication.ui.screen.PasswordInputScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val splashViewModel: SplashViewModel = viewModel()
                val passwordViewModel: PasswordViewModel = viewModel()
                val showSplash by splashViewModel.showSplash

                var memberName by remember { mutableStateOf<String?>(null) }

                if (showSplash) {
                    SplashScreen()
                } else if (memberName == null) {
                    PasswordInputScreen(
                        onSuccess = { name ->
                            memberName = name
                        },
                        viewModel = passwordViewModel
                    )
                } else {
                    SuccessScreen(
                        name = memberName!!,
                        onLogout = {
                            memberName = null
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun SuccessScreen(name: String, onLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "이름: $name",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onLogout) {
            Text("로그아웃")
        }
    }
}
