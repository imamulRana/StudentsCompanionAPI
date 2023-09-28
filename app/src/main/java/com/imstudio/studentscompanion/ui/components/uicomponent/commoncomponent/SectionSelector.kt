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
fun SectionSelector(
    modifier: Modifier = Modifier,
    studentsCompanionViewModel: StudentsCompanionViewModel
) {
    val loginUiState by studentsCompanionViewModel.loginUiState.collectAsState()
    val sectionState by studentsCompanionViewModel.sections.collectAsState()

    var currentSection by remember {
        mutableStateOf(loginUiState.section)
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
            value = loginUiState.section.section,
            onValueChange = { currentSection.section },
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
            placeholder = { Text(text = "Section") }
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            sectionState.forEach {
                DropdownMenuItem(
                    text = {
                        Text(text = it.section)
                    },
                    onClick = {
                        currentSection = it
                        studentsCompanionViewModel.getAllClass(
                            currentSection.id,
                            currentSection.section
                        )
                        expanded = !expanded
                    }
                )
            }
        }
    }

//    LazyRow(
//        horizontalArrangement = Arrangement.spacedBy(Padding.mediumPadding),
//        contentPadding = PaddingValues(horizontal = Padding.largePadding) //15.dp
//    ) {
//        items(sectionState.size) {
//            Box(
//                modifier = modifier
//                    .size(50.dp)
//                    .fillMaxSize()
//                    .clip(MaterialTheme.shapes.extraLarge)
//                    .clickable {
//                        currentSection = sectionState[it]
//                        studentsCompanionViewModel.getAllClass(
//                            currentSection.id,
//                            currentSection.section
//                        )
//                    }
//                    .border(
//                        2.dp,
//                        MaterialTheme.colorScheme.primary,
//                        MaterialTheme.shapes.extraLarge
//                    )
//                    .background(
//                        if (loginUiState.section.section == sectionState[it].section) MaterialTheme.colorScheme.primary
//                        else MaterialTheme.colorScheme.surface
//                    ),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = sectionState[it].section.uppercase(),
//                    style = MaterialTheme.typography.bodyLarge,
//                )
//            }
//        }
//    }
}