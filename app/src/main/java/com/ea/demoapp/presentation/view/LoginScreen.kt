package com.ea.demoapp.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ea.demoapp.R
import com.ea.demoapp.presentation.Screen

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(stringResource(R.string.enter_your_username_email)) },
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = { navController.navigate(Screen.Greetings.createRoute(username)) },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(stringResource(R.string.login))
        }
    }
}
