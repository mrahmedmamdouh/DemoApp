package com.ea.demoapp.data

import com.ea.demoapp.data.datasource.LocalDataSource
import com.ea.demoapp.data.datasource.MedicineEntity
import com.ea.demoapp.data.datasource.RemoteDataSource
import com.ea.demoapp.data.dto.ClassName
import com.ea.demoapp.data.dto.Diabetes
import com.ea.demoapp.data.dto.Drug
import com.ea.demoapp.data.dto.Medication
import com.ea.demoapp.data.dto.MedicationClass
import com.ea.demoapp.data.dto.MedicineResponse
import com.ea.demoapp.data.dto.Problem
import com.ea.demoapp.data.repo.RepositoryImpl
import com.ea.demoapp.domain.entity.Medicine
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RepositoryImplTest {

    private val remoteDataSource: RemoteDataSource = mockk()
    private val localDataSource: LocalDataSource = mockk()
    private val repository = RepositoryImpl(remoteDataSource, localDataSource)

    @Test
    fun `getMedicines should fetch from remote and save to local`() = runBlocking {
        // Arrange
        val response = MedicineResponse(
            problems = listOf(
                Problem(
                    Diabetes = listOf(
                        Diabetes(
                            medications = listOf(
                                Medication(
                                    medicationsClasses = listOf(
                                        MedicationClass(
                                            className = listOf(
                                                ClassName(
                                                    associatedDrug = listOf(
                                                        Drug(name = "Aspirin", dose = "500mg", strength = "Strong")
                                                    ),
                                                    associatedDrug2 = emptyList()
                                                )
                                            ),
                                            className2 = emptyList()
                                        )
                                    )
                                )
                            ),
                            labs = emptyList()
                        )
                    ),
                    Asthma = emptyList()
                )
            )
        )
        val domainMedicines = listOf(
            Medicine(name = "Aspirin", dose = "500mg", strength = "Strong")
        )
        val entities = domainMedicines.map { MedicineEntity(it.name, it.dose, it.strength) }

        // Mock behaviors
        coEvery { remoteDataSource.fetchMedicines() } returns response
        coEvery { localDataSource.saveMedicines(entities) } returns Unit
        coEvery { localDataSource.getMedicines() } returns emptyList()

        // Act
        val result = repository.getMedicines()

        // Assert
        assertEquals(domainMedicines, result)
        coVerify { remoteDataSource.fetchMedicines() }
        coVerify { localDataSource.saveMedicines(entities) }
    }



    @Test
    fun `getMedicines should fetch from local when remote fails`() = runBlocking {
        val localEntities = listOf(
            MedicineEntity(name = "Aspirin", dose = "500mg", strength = "Strong")
        )
        val domainMedicines = localEntities.map { Medicine(it.name, it.dose, it.strength) }

        coEvery { remoteDataSource.fetchMedicines() } throws RuntimeException("Network Error")
        coEvery { localDataSource.getMedicines() } returns localEntities

        // Act
        val result = repository.getMedicines()

        // Assert
        assertEquals(domainMedicines, result)
        coVerify { localDataSource.getMedicines() }
    }
}
