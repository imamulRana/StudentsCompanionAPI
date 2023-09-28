package com.imstudio.studentscompanion.model

data class LoginUiState(
    val department: Department = Department(0, ""),
    val batch: Batch = Batch(0, ""),
    val section: Section = Section(0, "")
)
