package com.ea.demoapp.presentation.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ea.demoapp.R
import com.ea.demoapp.presentation.Screen
import com.ea.demoapp.presentation.model.MedicineIntent
import com.ea.demoapp.presentation.state.MedicinesState
import com.ea.demoapp.presentation.viewmodel.MedicinesViewModel
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GreetingsScreen(
    navController: NavController,
    username: String,
    viewModel: MedicinesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val greetingMessage = when (LocalTime.now().hour) {
        in 0..11 -> stringResource(R.string.good_morning)
        in 12..17 -> stringResource(R.string.good_afternoon)
        else -> stringResource(R.string.good_evening)
    }

    when (state) {
        is MedicinesState.Loading -> {
            LoadingView()
        }
        is MedicinesState.Success -> {
            val medicines = (state as MedicinesState.Success).medicines
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp, top = 24.dp),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$greetingMessage, $username!",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = stringResource(R.string.here_are_your_medicines),
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(medicines) { medicine ->
                        MedicineCard(medicine = medicine, onClick = {
                            navController.navigate(
                                Screen.Details.createRoute(
                                    name = medicine.name,
                                    dose = medicine.dose,
                                    strength = medicine.strength
                                )
                            )
                        })
                    }
                }
            }
        }
        is MedicinesState.Error -> {
            val errorMessage = (state as MedicinesState.Error).message
            ErrorView(message = errorMessage)
        }
    }
}

@Composable
fun MedicineCard(medicine: MedicineIntent, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Name: ${medicine.name}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Dose: ${medicine.dose}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Strength: ${medicine.strength}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
