package com.ea.demoapp.data.datasource

import com.ea.demoapp.data.dto.MedicineResponse
import retrofit2.http.GET
import jakarta.inject.Inject

interface MedicineApi {
    @GET("v3/85d70e53-3eda-43b3-8932-20a0b26fd380")
    suspend fun getMedicines(): MedicineResponse
}

class RemoteDataSource @Inject constructor(private val api: MedicineApi) {
    suspend fun fetchMedicines(): MedicineResponse = api.getMedicines()
}
