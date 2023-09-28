package com.imstudio.studentscompanion.model

import com.google.gson.annotations.SerializedName

data class ClassItem(
    @SerializedName("class_code")
    val classCode: String = "",

    @SerializedName("class_subject")
    val classSubject: String = "",

    @SerializedName("teacher_init")
    val teacherInit: String = "",

    @SerializedName("class_start")
    val classStart: String = "",

    @SerializedName("class_end")
    val classEnd: String = "",

    @SerializedName("class_room")
    val classRoom: String = "",
)
