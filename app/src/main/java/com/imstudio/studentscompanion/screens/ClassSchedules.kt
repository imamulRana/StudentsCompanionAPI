package com.imstudio.studentscompanion.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.imstudio.studentscompanion.StudentsCompanionViewModel
import com.imstudio.studentscompanion.ui.components.uicomponent.classcomponent.ClassPager

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClassSchedules(
    studentsCompanionViewModel: StudentsCompanionViewModel
) {
    val state = rememberPagerState()
    val classes by studentsCompanionViewModel.classes.collectAsState()
    ClassPager(state = state, classes = classes)
}