package com.ea.demoapp.data

import com.ea.demoapp.data.datasource.LocalDataSource
import com.ea.demoapp.data.datasource.MedicineDao
import com.ea.demoapp.data.datasource.MedicineEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LocalDataSourceTest {

    private val dao = mock<MedicineDao>()
    private val localDataSource = LocalDataSource(dao)

    @Test
    fun `getMedicines should return all medicines from DAO`(): Unit = runBlocking {
        val medicines = listOf(
            MedicineEntity("Aspirin", "500mg", "Strong"),
            MedicineEntity("Ibuprofen", "200mg", "Moderate")
        )
        whenever(dao.getAllMedicines()).thenReturn(medicines)

        val result = localDataSource.getMedicines()

        assertEquals(medicines, result)
        verify(dao).getAllMedicines()
    }

    @Test
    fun `saveMedicines should save medicines to DAO`(): Unit = runBlocking {
        val medicines = listOf(
            MedicineEntity("Aspirin", "500mg", "Strong")
        )

        localDataSource.saveMedicines(medicines)

        verify(dao).insertAll(medicines)
    }
}
