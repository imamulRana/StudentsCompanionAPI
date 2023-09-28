package com.imstudio.studentscompanion.model

import com.google.gson.annotations.SerializedName

data class Batch(
    @SerializedName("batch_id")
    val id: Int,
    @SerializedName("batch_name")
    val batch: String
)
