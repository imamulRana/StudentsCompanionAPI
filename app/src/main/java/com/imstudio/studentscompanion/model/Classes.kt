package com.imstudio.studentscompanion.model

import com.google.gson.annotations.SerializedName

data class Classes(
    val day: String = "",
    @SerializedName("class_item")
    val classItem: List<ClassItem> = listOf(ClassItem())
)
