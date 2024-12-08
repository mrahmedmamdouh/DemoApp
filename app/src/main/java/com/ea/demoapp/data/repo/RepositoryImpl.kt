package com.ea.demoapp.data.repo

import com.ea.demoapp.data.datasource.LocalDataSource
import com.ea.demoapp.data.datasource.MedicineEntity
import com.ea.demoapp.data.datasource.RemoteDataSource
import com.ea.demoapp.data.mapper.toDomainMedicines
import com.ea.demoapp.domain.entity.Medicine
import com.ea.demoapp.domain.repo.MedicineRepository
import jakarta.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MedicineRepository {

    override suspend fun getMedicines(): List<Medicine> {
        return try {
            val response = remoteDataSource.fetchMedicines()
            val medicines = response.toDomainMedicines()
            localDataSource.saveMedicines(medicines.map { it.toEntity() })
            medicines
        } catch (e: Exception) {
            localDataSource.getMedicines().map { it.toDomain() }
        }
    }

    private fun Medicine.toEntity() = MedicineEntity(name, dose, strength)
    private fun MedicineEntity.toDomain() = Medicine(name, dose, strength)
}
