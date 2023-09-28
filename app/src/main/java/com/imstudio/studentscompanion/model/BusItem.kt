package com.imstudio.studentscompanion.model

import com.google.gson.annotations.SerializedName

data class BusItem(
    @SerializedName("time_of_day")
    val busTime: String = "",
    @SerializedName("route_name")
    val busRoute: String = "",
    @SerializedName("bus_number")
    val busNumber: String = ""
)