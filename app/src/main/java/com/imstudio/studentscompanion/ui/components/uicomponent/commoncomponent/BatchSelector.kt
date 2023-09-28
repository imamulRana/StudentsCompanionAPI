package com.imstudio.studentscompanion.ui.components.uicomponent.commoncomponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.imstudio.studentscompanion.StudentsCompanionViewModel
import com.imstudio.studentscompanion.ui.components.modifiers.Padding

@OptIn(ExperimentalMaterial3Api::class)
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
    var expanded by remember {
        mutableStateOf(false)
    }
    ExposedDropdownMenuBox(
        expanded = expanded, onExpandedChange = { expanded = !expanded },
        modifier = modifier
            .padding(horizontal = Padding.mediumPadding)
    ) {
        OutlinedTextField(
            value = loginUiState.batch.batch,
            onValueChange = { currentBatch.batch },
            modifier = modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                if (expanded) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowUp,
                        contentDescription = ""
                    )
                } else Icon(imageVector = Icons.Rounded.KeyboardArrowDown, contentDescription = "")
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            shape = MaterialTheme.shapes.medium,
            placeholder = { Text(text = "Batch") }
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            batchState.forEach {
                DropdownMenuItem(
                    text = { Text(text = it.batch) },
                    onClick = {
                        currentBatch = it
                        studentsCompanionViewModel.getSectionByBatch(
                            currentBatch.id,
                            currentBatch.batch
                        )
                        expanded = !expanded
                    }
                )
            }
        }
    }
//    LazyRow(
//        horizontalArrangement = Arrangement.spacedBy(Padding.mediumPadding),
//        contentPadding = PaddingValues(horizontal = Padding.largePadding)
//    ) {
//        items(batchState.size) {
//            Box(
//                modifier = modifier
//                    .size(50.dp)
//                    .fillMaxSize()
//                    .clip(MaterialTheme.shapes.extraLarge)
//                    .clickable {
//                        currentBatch = batchState[it]
//                        studentsCompanionViewModel.getSectionByBatch(
//                            currentBatch.id,
//                            currentBatch.batch
//                        )
//                    }
//                    .border(
//                        2.dp,
//                        MaterialTheme.colorScheme.primary,
//                        MaterialTheme.shapes.extraLarge
//                    )
//                    .background(
//                        if (loginUiState.batch.id == batchState[it].id) MaterialTheme.colorScheme.primary
//                        else MaterialTheme.colorScheme.surface
//                    ),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = batchState[it].batch.uppercase(),
//                    style = MaterialTheme.typography.bodyLarge,
//                )
//            }
//        }
//    }
}