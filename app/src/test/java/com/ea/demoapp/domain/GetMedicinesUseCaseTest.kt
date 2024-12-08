package com.ea.demoapp.domain

import com.ea.demoapp.domain.entity.Medicine
import com.ea.demoapp.domain.repo.MedicineRepository
import com.ea.demoapp.domain.usecase.GetMedicinesUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.*

class GetMedicinesUseCaseTest {

    private val repository = mock<MedicineRepository>()
    private val useCase = GetMedicinesUseCase(repository)

    @Test
    fun `execute should fetch medicines from repository`(): Unit = runBlocking {
        // Arrange
        val medicines = listOf(
            Medicine(name = "Aspirin", dose = "500mg", strength = "Strong"),
            Medicine(name = "Ibuprofen", dose = "200mg", strength = "Moderate")
        )
        whenever(repository.getMedicines()).thenReturn(medicines)

        // Act
        val result = useCase.execute()

        // Assert
        assertEquals(medicines, result)
        verify(repository).getMedicines()
    }

    @Test(expected = Exception::class)
    fun `execute should propagate exceptions from repository`(): Unit = runBlocking {
        // Arrange
        whenever(repository.getMedicines()).thenThrow(RuntimeException("Network error"))

        // Act
        useCase.execute()
    }
}
