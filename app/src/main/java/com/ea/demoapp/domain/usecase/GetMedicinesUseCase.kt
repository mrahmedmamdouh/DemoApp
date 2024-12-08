package com.ea.demoapp.domain.usecase

import com.ea.demoapp.domain.entity.Medicine
import com.ea.demoapp.domain.repo.MedicineRepository

open class GetMedicinesUseCase(private val repository: MedicineRepository) {
    /**
     * Executes the use case to fetch medicines.
     * @return List of Medicine
     */
    open suspend fun execute(): List<Medicine> {
        return repository.getMedicines()
    }
}
