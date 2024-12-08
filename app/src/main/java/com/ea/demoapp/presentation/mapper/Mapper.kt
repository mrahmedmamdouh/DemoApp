package com.ea.demoapp.presentation.mapper

import com.ea.demoapp.domain.entity.Medicine
import com.ea.demoapp.presentation.model.MedicineIntent

fun Medicine.toIntent(): MedicineIntent = MedicineIntent(
    name = this.name,
    dose = this.dose,
    strength = this.strength
)
