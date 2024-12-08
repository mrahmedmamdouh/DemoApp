package com.ea.demoapp.data.datasource

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import jakarta.inject.Inject

@Entity(tableName = "medicine")
data class MedicineEntity(
    @PrimaryKey val name: String,
    val dose: String,
    val strength: String
)

@Dao
interface MedicineDao {
    @Query("SELECT * FROM medicine")
    suspend fun getAllMedicines(): List<MedicineEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(medicines: List<MedicineEntity>)
}

class LocalDataSource @Inject constructor(private val dao: MedicineDao) {
    suspend fun getMedicines(): List<MedicineEntity> = dao.getAllMedicines()
    suspend fun saveMedicines(medicines: List<MedicineEntity>) = dao.insertAll(medicines)
}
