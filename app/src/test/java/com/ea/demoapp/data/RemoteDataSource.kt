package com.ea.demoapp.data

import com.ea.demoapp.data.datasource.MedicineApi
import com.ea.demoapp.data.datasource.RemoteDataSource
import com.ea.demoapp.data.dto.MedicineResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class RemoteDataSourceTest {

    private val api = mock<MedicineApi>()
    private val remoteDataSource = RemoteDataSource(api)

    @Test
    fun `fetchMedicines should return medicines from API`(): Unit = runBlocking {
        val response = MedicineResponse(problems = emptyList())
        whenever(api.getMedicines()).thenReturn(response)

        val result = remoteDataSource.fetchMedicines()

        assertEquals(response, result)
        verify(api).getMedicines()
    }
}
