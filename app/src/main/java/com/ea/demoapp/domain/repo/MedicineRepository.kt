package com.ea.demoapp.domain.repo

import com.ea.demoapp.domain.entity.Medicine


interface MedicineRepository {
    /**
     * Fetches all medicines from a remote or local source.
     * @return List of Medicine
     */
    suspend fun getMedicines(): List<Medicine>
}
