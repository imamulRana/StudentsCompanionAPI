package com.imstudio.studentscompanion.model

import com.google.gson.annotations.SerializedName

data class Bus(
    val day: String = "",
    @SerializedName("bus_schedules")
    val busItem: List<BusItem> = listOf(BusItem())
)
