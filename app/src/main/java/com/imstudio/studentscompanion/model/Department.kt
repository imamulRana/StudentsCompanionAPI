package com.imstudio.studentscompanion.model

import com.google.gson.annotations.SerializedName

data class Department(
    @SerializedName("department_id")
    val id: Int,
    @SerializedName("department_name")
    val department: String
)
