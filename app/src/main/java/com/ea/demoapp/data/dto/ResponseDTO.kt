package com.ea.demoapp.data.dto

import com.google.gson.annotations.SerializedName

data class MedicineResponse(
    val problems: List<Problem>
)

data class Problem(
    val Diabetes: List<Diabetes>?,
    val Asthma: List<Any>?
)

data class Diabetes(
    val medications: List<Medication>?,
    val labs: List<Lab>?
)

data class Medication(
    val medicationsClasses: List<MedicationClass>?
)

data class MedicationClass(
    val className: List<ClassName>?,
    val className2: List<ClassName>?
)

data class ClassName(
    val associatedDrug: List<Drug>?,
    @SerializedName("associatedDrug#2")
    val associatedDrug2: List<Drug>?
)

data class Drug(
    val name: String?,
    val dose: String?,
    val strength: String?
)

data class Lab(
    val missing_field: String?
)
