package com.ea.demoapp.data

import com.ea.demoapp.data.dto.*
import com.ea.demoapp.data.mapper.toDomainMedicines
import com.ea.demoapp.domain.entity.Medicine
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperTest {

    @Test
    fun `toDomainMedicines should map MedicineResponse to list of domain medicines`() {
        val response = MedicineResponse(
            problems = listOf(
                Problem(
                    Diabetes = listOf(
                        Diabetes(
                            medications = listOf(
                                Medication(
                                    medicationsClasses = listOf(
                                        MedicationClass(
                                            className = listOf(
                                                ClassName(
                                                    associatedDrug = listOf(
                                                        Drug("Aspirin", "500mg", "Strong")
                                                    ),
                                                    associatedDrug2 = listOf(
                                                        Drug("Ibuprofen", "200mg", "Moderate")
                                                    )
                                                )
                                            ),
                                            className2 = null
                                        )
                                    )
                                )
                            ),
                            labs = emptyList()
                        )
                    ),
                    Asthma = emptyList()
                )
            )
        )

        val result = response.toDomainMedicines()

        val expected = listOf(
            Medicine("Aspirin", "500mg", "Strong"),
            Medicine("Ibuprofen", "200mg", "Moderate")
        )
        assertEquals(expected, result)
    }
}
