package com.imstudio.studentscompanion

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imstudio.studentscompanion.model.Batch
import com.imstudio.studentscompanion.model.Bus
import com.imstudio.studentscompanion.model.Classes
import com.imstudio.studentscompanion.model.Department
import com.imstudio.studentscompanion.model.LoginUiState
import com.imstudio.studentscompanion.model.Section
import com.imstudio.studentscompanion.repository.StuCompRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class StudentsCompanionViewModel(private val stuCompRepo: StuCompRepo) : ViewModel() {


    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    private val _classes = MutableStateFlow(listOf(Classes()))
    val classes: StateFlow<List<Classes>> = _classes.asStateFlow()

    private val _departments = MutableStateFlow<List<Department>>(listOf())
    val departments: StateFlow<List<Department>> = _departments.asStateFlow()

    private val _batches = MutableStateFlow<List<Batch>>(listOf())
    val batches: StateFlow<List<Batch>> = _batches.asStateFlow()

    private val _sections = MutableStateFlow<List<Section>>(listOf())
    val sections: StateFlow<List<Section>> = _sections.asStateFlow()

    //Bus
    private val _upData = MutableStateFlow(listOf(Bus()))
    val upData: StateFlow<List<Bus>> = _upData.asStateFlow()

    private val _downData = MutableStateFlow(listOf(Bus()))
    val downData: StateFlow<List<Bus>> = _downData.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()


    fun getDeptByInit() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newList = stuCompRepo.getAllDept()
                _isLoading.update { false }
                _departments.compareAndSet(listOf(), newList)
            } catch (_: IOException) {
            }
        }
    }

    fun getBatchByDept(deptId: Int, dept: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newList = stuCompRepo.getAllBatch(deptId)
                _batches.update {
                    newList
                }
                _loginUiState.update { loginUiState ->
                    loginUiState.copy(
                        department = Department(deptId, dept), batch = Batch(0, ""),
                        section = Section(0, "")
                    )
                }
            } catch (_: IOException) {
            }
        }
    }

    fun getSectionByBatch(batchId: Int, batch: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newList =
                    stuCompRepo.getAllSection(dept = _loginUiState.value.department.id, batchId)
                _sections.update {
                    newList
                }
                _loginUiState.update { loginUiState ->
                    loginUiState
                        .copy(
                            department = _loginUiState.value.department,
                            batch = Batch(batchId, batch),
                            section = Section(0, "")
                        )
                }
            } catch (_: IOException) {
            }
        }
    }

    fun getAllClass(sectionId: Int, section: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newList = stuCompRepo.getAllClass(
                    _loginUiState.value.department.id,
                    _loginUiState.value.batch.id,
                    sectionId
                )

                _classes.update {
                    newList
                }
                Log.d("Classes", _classes.value.toString())
                _loginUiState.update { loginUiState ->
                    loginUiState
                        .copy(
                            department = _loginUiState.value.department,
                            batch = _loginUiState.value.batch,
                            section = Section(sectionId, section)
                        )
                }
            } catch (_: IOException) {
            }
        }
    }

    fun getUpData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newList = stuCompRepo.getUpData()
                _upData.update {
                    newList
                }
            } catch (_: IOException) {
            }
        }
    }

    fun getDownData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newList = stuCompRepo.getDownData()
                _downData.update {
                    newList
                }
            } catch (_: IOException) {
            }
        }
    }

    fun updateLoginUiState(loginUiState: LoginUiState) {
        _loginUiState.update {
            loginUiState
        }
    }
}