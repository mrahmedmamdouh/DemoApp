package com.ea.demoapp.data.mapper

import com.ea.demoapp.data.dto.MedicineResponse
import com.ea.demoapp.domain.entity.Medicine

fun MedicineResponse.toDomainMedicines(): List<Medicine> {
    val medicines = mutableListOf<Medicine>()
    problems.forEach { problem ->
        problem.Diabetes?.forEach { diabetes ->
            diabetes.medications?.forEach { medication ->
                medication.medicationsClasses?.forEach { medicationClass ->
                    medicationClass.className?.forEach { className ->
                        className.associatedDrug?.forEach { drug ->
                            medicines.add(
                                Medicine(
                                    name = drug.name ?: "Unknown",
                                    dose = drug.dose ?: "Unknown",
                                    strength = drug.strength ?: "Unknown"
                                )
                            )
                        }
                        className.associatedDrug2?.forEach { drug ->
                            medicines.add(
                                Medicine(
                                    name = drug.name ?: "Unknown",
                                    dose = drug.dose ?: "Unknown",
                                    strength = drug.strength ?: "Unknown"
                                )
                            )
                        }
                    }
                    medicationClass.className2?.forEach { className ->
                        className.associatedDrug?.forEach { drug ->
                            medicines.add(
                                Medicine(
                                    name = drug.name ?: "Unknown",
                                    dose = drug.dose ?: "Unknown",
                                    strength = drug.strength ?: "Unknown"
                                )
                            )
                        }
                        className.associatedDrug2?.forEach { drug ->
                            medicines.add(
                                Medicine(
                                    name = drug.name ?: "Unknown",
                                    dose = drug.dose ?: "Unknown",
                                    strength = drug.strength ?: "Unknown"
                                )
                            )
                        }
                    }
                }
            }
        }
    }
    return medicines
}
