package com.ea.demoapp.presentation

import com.ea.demoapp.FakeGetMedicinesUseCase
import com.ea.demoapp.domain.entity.Medicine
import com.ea.demoapp.presentation.model.MedicineIntent
import com.ea.demoapp.presentation.state.MedicinesState
import com.ea.demoapp.presentation.viewmodel.MedicinesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MedicinesViewModelTest {

    private lateinit var viewModel: MedicinesViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadMedicines emits Loading then Success when use case returns data`() = runTest {
        // Arrange
        val medicines = listOf(
            Medicine(name = "Aspirin", dose = "500mg", strength = "Strong"),
            Medicine(name = "Ibuprofen", dose = "200mg", strength = "Moderate")
        )
        val fakeUseCase = FakeGetMedicinesUseCase(medicines)

        // Act
        viewModel = MedicinesViewModel(fakeUseCase)
        val result = viewModel.state.first { it is MedicinesState.Success } as MedicinesState.Success

        // Assert
        val expected = medicines.map { MedicineIntent(it.name, it.dose, it.strength) }
        assertEquals(MedicinesState.Success(expected), result)
    }

    @Test
    fun `loadMedicines emits Loading then Error when use case throws exception`() = runTest {
        // Arrange
        val errorMessage = "Network error"
        val fakeUseCase = FakeGetMedicinesUseCase(exception = RuntimeException(errorMessage))

        // Act
        viewModel = MedicinesViewModel(fakeUseCase)
        val result = viewModel.state.first { it is MedicinesState.Error } as MedicinesState.Error

        // Assert
        assertEquals(MedicinesState.Error(errorMessage), result)
    }
}
