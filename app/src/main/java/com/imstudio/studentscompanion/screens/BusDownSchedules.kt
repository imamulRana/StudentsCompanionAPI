package com.imstudio.studentscompanion.screens


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.imstudio.studentscompanion.StudentsCompanionViewModel
import com.imstudio.studentscompanion.ui.components.uicomponent.buscomponent.BusPager

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BusDownSchedules(studentsCompanionViewModel: StudentsCompanionViewModel) {
    val state = rememberPagerState()
    val bus by studentsCompanionViewModel.downData.collectAsState()
    BusPager(state = state, bus = bus)
}