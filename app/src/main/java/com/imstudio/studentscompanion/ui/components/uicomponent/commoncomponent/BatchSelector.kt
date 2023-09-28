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
fun BatchSelector(
    modifier: Modifier = Modifier,
    studentsCompanionViewModel: StudentsCompanionViewModel
) {
    val loginUiState by studentsCompanionViewModel.loginUiState.collectAsState()
    val batchState by studentsCompanionViewModel.batches.collectAsState()
    var currentBatch by remember {
        mutableStateOf(loginUiState.batch)
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(Padding.mediumPadding),
        contentPadding = PaddingValues(horizontal = Padding.largePadding)
    ) {
        items(batchState.size) {
            Box(
                modifier = modifier
                    .size(50.dp)
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.extraLarge)
                    .clickable {
                        currentBatch = batchState[it]
                        studentsCompanionViewModel.getSectionByBatch(
                            currentBatch.id,
                            currentBatch.batch
                        )
                    }
                    .border(
                        2.dp,
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.shapes.extraLarge
                    )
                    .background(
                        if (loginUiState.batch.id == batchState[it].id) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surface
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = batchState[it].batch.uppercase(),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}