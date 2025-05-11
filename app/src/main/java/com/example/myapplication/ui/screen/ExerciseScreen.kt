package com.example.myapplication.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.viewmodel.ExerciseViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ExerciseScreen(
    userName: String,
    viewModel: ExerciseViewModel = viewModel(),
    onLogout: () -> Unit,
    onStartWorkout: () -> Unit
) {
    BackHandler(enabled = true) { }

    val state = viewModel.uiState
    val modes = viewModel.modes

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF232323))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = userName,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                  viewModel.resetState()
                  onLogout()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                modifier = Modifier.width(128.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("로그아웃", color = Color.White)
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "운동 모드",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(modes) { idx, mode ->
                val isSelected = idx == state.selectedModeIndex
                Button(
                    onClick = { viewModel.selectMode(idx) },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) Color(0xFF4B89DC) else Color.DarkGray
                    ),
                    modifier = Modifier.height(48.dp)
                ) {
                    Text(
                        text = mode,
                        color = Color.White
                    )
                }
            }
        }
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2D2D))
        ) {
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = modes[state.selectedModeIndex],
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = when (modes[state.selectedModeIndex]) {
                        "일반" -> "일반적인 무게추의 느낌을 재현합니다.\n무게 날림을 자동으로 보정합니다."
                        "네거티브" -> "최대 수축 이후에 무게를 증가시킵니다.\n최대한 버티면서 이완하세요."
                        "포지티브" -> "최대 수축 이전까지 무게를 증가시킵니다.\n이완을 시작하면 원상태로 돌아옵니다."
                        "밴드" -> "탄성을 가진 밴드처럼 가동 범위를 따라 무게가 변합니다."
                        "체인" -> "쇠사슬을 이용한 훈련법을 재현합니다.\n가동 범위의 70%까지 무게가 일정하게 증가했다가 감소합니다."
                        "드롭" -> "사용자가 한계에 도달할 때마다 무게를 낮춥니다.\n한 세트에서 최대 4번까지 낮춥니다."
                        "워터" -> "물속에서 움직이는 것처럼 빠르게 움직일수록 저항이 강해집니다."
                        "등속성" -> "일정한 속도로 운동합니다.\n부상 위험이 낮고 재활에 효과적입니다."
                        else -> ""
                    },
                    color = Color.Gray,
                    fontSize = 10.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                if (modes[state.selectedModeIndex] == "일반" || modes[state.selectedModeIndex] == "등속성") {
                    Text(
                        text = "세부 설정 필요 없음",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if(modes[state.selectedModeIndex] == "밴드"){
                            (1..5).forEach { level ->
                                Button(
                                    onClick = { viewModel.setDetailLevel(level) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (state.detailLevel == level) Color(
                                            0xFF4B89DC
                                        ) else Color.DarkGray
                                    ),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(40.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(
                                        "$level 단계",
                                        color = Color.White,
                                        maxLines = 1,
                                        softWrap = false,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            }
                        }
                        else {
                            (1..3).forEach { level ->
                                Button(
                                    onClick = { viewModel.setDetailLevel(level) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (state.detailLevel == level) Color(
                                            0xFF4B89DC
                                        ) else Color.DarkGray
                                    ),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(40.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(
                                        "$level 단계",
                                        color = Color.White,
                                        maxLines = 1,
                                        softWrap = false,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "운동 강도",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
            val totalVolume = state.weight * state.sets * state.reps
            Text(
                text = "총 볼륨 : $totalVolume kg",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2D2D))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("무게", color = Color.White)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        onClick = { viewModel.changeWeight(-5) },
                        shape = CircleShape,
                        modifier = Modifier.size(40.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                    ) { Text("<<", color = Color.White) }
                    Button(
                        onClick = { viewModel.changeWeight(-1) },
                        shape = CircleShape,
                        modifier = Modifier.size(40.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                    ) { Text("<", color = Color.White) }
                    Text(
                        text = "${state.weight}kg",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Button(
                        onClick = { viewModel.changeWeight(1) },
                        shape = CircleShape,
                        modifier = Modifier.size(40.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                    ) { Text(">", color = Color.White) }
                    Button(
                        onClick = { viewModel.changeWeight(5) },
                        shape = CircleShape,
                        modifier = Modifier.size(40.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                    ) { Text(">>", color = Color.White) }
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2D2D))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "안전보조",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                val options = listOf("꺼짐", "최소", "최대")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    options.forEachIndexed { idx, label ->
                        Button(
                            onClick = { viewModel.setSafetyAssist(idx) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (state.safetyAssist == idx) Color(0xFF4B89DC) else Color.DarkGray
                            ),
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = label,
                                color = Color.White,
                                maxLines = 1,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WorkoutParam(
                label = "세트",
                value = "${state.sets}",
                onMinus = { viewModel.changeSets(-1) },
                onPlus = { viewModel.changeSets(1) }
            )
            WorkoutParam(
                label = "횟수",
                value = "${state.reps}",
                onMinus = { viewModel.changeReps(-1) },
                onPlus = { viewModel.changeReps(1) }
            )
            WorkoutParam(
                label = "휴식",
                value = "${state.restSeconds / 60}:${(state.restSeconds % 60).toString().padStart(2, '0')}",
                onMinus = { viewModel.changeRest(-5) },
                onPlus = { viewModel.changeRest(5) }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onStartWorkout() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4B89DC))
        ) {
            Text("운동 시작하기", color = Color.White)
        }
    }
}

@Composable
fun WorkoutParam(
    label: String,
    value: String,
    onMinus: () -> Unit,
    onPlus: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2D2D)),
        modifier = Modifier.width(120.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            Text(label, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = onMinus,
                    modifier = Modifier.size(32.dp)
                ) { Text("-", color = Color.White) }
                Text(value, color = Color.White)
                IconButton(
                    onClick = onPlus,
                    modifier = Modifier.size(32.dp)
                ) { Text("+", color = Color.White) }
            }
        }
    }
}
