package com.example.myapplication.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.viewmodel.PasswordViewModel
import com.example.myapplication.data.MemberRepository
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PasswordInputScreen(
    onSuccess: (String) -> Unit,
    viewModel: PasswordViewModel = viewModel()
) {
    val password by viewModel.password
    val isValid by viewModel.isValid
    var showError by remember { mutableStateOf(false) }

    LaunchedEffect(isValid) {
        if (isValid == true && password.length == 4) {
            val member = MemberRepository.findMemberByNumber(password)
            if (member != null) {
                onSuccess(member.name)
                viewModel.clear()
            } else {
                showError = true
                viewModel.clear()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "회원번호를 입력하세요",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(4) { idx ->
                Text(
                    text = if (idx < password.length) "●" else "○",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        val digits = listOf(
            listOf("1", "2", "3"),
            listOf("4", "5", "6"),
            listOf("7", "8", "9"),
            listOf("", "0", "")
        )

        for (row in digits) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                for (digit in row) {
                    if (digit.isNotEmpty()) {
                        Button(
                            onClick = { viewModel.appendDigit(digit) },
                            enabled = password.length < 4,
                            modifier = Modifier
                                .size(64.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray
                            ),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = digit,
                                color = Color.White,
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.size(64.dp))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.clear()
            showError = false
        },colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray
                ),) {
            Text("Clear")
        }

        Spacer(modifier = Modifier.height(24.dp))
        if (showError) {
            Text(
                text = "존재하지 않는 회원번호입니다.",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
