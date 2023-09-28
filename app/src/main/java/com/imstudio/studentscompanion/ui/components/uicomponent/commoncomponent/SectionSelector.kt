package com.imstudio.studentscompanion.ui.components.uicomponent.commoncomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.imstudio.studentscompanion.StudentsCompanionViewModel
import com.imstudio.studentscompanion.ui.components.modifiers.Padding

@Composable
fun SectionSelector(
    modifier: Modifier = Modifier,
    studentsCompanionViewModel: StudentsCompanionViewModel
) {
    val loginUiState by studentsCompanionViewModel.loginUiState.collectAsState()
    val sectionState by studentsCompanionViewModel.sections.collectAsState()

    var currentSection by remember {
        mutableStateOf(loginUiState.section)
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(Padding.mediumPadding),
        contentPadding = PaddingValues(horizontal = Padding.largePadding) //15.dp
    ) {
        items(sectionState.size) {
            Box(
                modifier = modifier
                    .size(50.dp)
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.extraLarge)
                    .clickable {
                        currentSection = sectionState[it]
                        studentsCompanionViewModel.getAllClass(
                            currentSection.id,
                            currentSection.section
                        )
                    }
                    .border(
                        2.dp,
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.shapes.extraLarge
                    )
                    .background(
                        if (loginUiState.section.section == sectionState[it].section) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surface
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = sectionState[it].section.uppercase(),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}