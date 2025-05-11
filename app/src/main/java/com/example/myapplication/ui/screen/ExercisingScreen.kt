package com.example.myapplication.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.ExerciseUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisingScreen(
    userName: String,
    state: ExerciseUiState,
    modes: List<String>,
    onBack: () -> Unit,
    onLogout: () -> Unit,
    onWeightChange: (Int) -> Unit
) {
    var set = 1
    var cnt = 0
    BackHandler(enabled = true) { onBack() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF232323))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "뒤로가기",
                    tint = Color.White
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = userName,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = onLogout,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    modifier = Modifier.width(128.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text("로그아웃", color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Surface(
            color = Color.White,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "$set 세트 중",
                color = Color.Black,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("$cnt/${state.reps}", color = Color.White, style = MaterialTheme.typography.headlineMedium)
            Text("${state.weight}kg", color = Color.White, style = MaterialTheme.typography.headlineMedium)
        }

        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "${state.weight}kg",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )
        Slider(
            value = state.weight.toFloat(),
            onValueChange = { newValue -> onWeightChange(newValue.toInt()) },
            valueRange = 0f..100f,
            steps = 0,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            thumb = {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.White, shape = CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                )
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2D2D)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(modes[state.selectedModeIndex], color = Color.White)
                Text(
                    when(state.safetyAssist) {
                        0 -> "꺼짐"
                        1 -> "최소"
                        2 -> "최대"
                        else -> ""
                    }, color = Color.White)
                Text("${state.sets} × ${state.reps}", color = Color.White)
                Text("${state.restSeconds / 60}:${"%02d".format(state.restSeconds % 60)}", color = Color.White)
            }

        }
    }
}
