package com.imstudio.studentscompanion.model

import com.google.gson.annotations.SerializedName

data class Section(
    @SerializedName("section_id")
    val id: Int,
    @SerializedName("section_name")
    val section: String
)
