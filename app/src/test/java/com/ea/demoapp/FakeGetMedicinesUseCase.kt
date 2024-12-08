package com.ea.demoapp

import com.ea.demoapp.domain.entity.Medicine
import com.ea.demoapp.domain.usecase.GetMedicinesUseCase
import org.mockito.kotlin.mock

class FakeGetMedicinesUseCase(
    private val medicines: List<Medicine> = emptyList(),
    private val exception: Exception? = null
) : GetMedicinesUseCase(mock()) {

    override suspend fun execute(): List<Medicine> {
        exception?.let { throw it }
        return medicines
    }
}
