package com.ea.demoapp.presentation.state

import com.ea.demoapp.presentation.model.MedicineIntent


sealed class MedicinesState {
    object Loading : MedicinesState()
    data class Success(val medicines: List<MedicineIntent>) : MedicinesState()
    data class Error(val message: String) : MedicinesState()
}
