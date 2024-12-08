package com.ea.demoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ea.demoapp.domain.usecase.GetMedicinesUseCase
import com.ea.demoapp.presentation.model.MedicineIntent
import com.ea.demoapp.presentation.state.MedicinesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicinesViewModel @Inject constructor(
    private val getMedicinesUseCase: GetMedicinesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MedicinesState>(MedicinesState.Loading)
    val state: StateFlow<MedicinesState> get() = _state

    init {
        loadMedicines()
    }

    private fun loadMedicines() {
        viewModelScope.launch {
            _state.value = MedicinesState.Loading
            try {
                val medicineList = getMedicinesUseCase.execute()
                val mappedMedicines = medicineList.map {
                    MedicineIntent(name = it.name, dose = it.dose, strength = it.strength)
                }
                _state.value = MedicinesState.Success(mappedMedicines)
            } catch (e: Exception) {
                _state.value = MedicinesState.Error(e.localizedMessage ?: "An unexpected error occurred")
            }
        }
    }
}
