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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
fun DepartmentSelector(
    modifier: Modifier = Modifier,
    studentsCompanionViewModel: StudentsCompanionViewModel
) {
    val loginUiState by studentsCompanionViewModel.loginUiState.collectAsState()
    val departmentState by studentsCompanionViewModel.departments.collectAsState()
    var currentDept by remember {
        mutableStateOf(loginUiState.department)
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded, onExpandedChange = { expanded = !expanded },
        modifier = modifier
            .padding(horizontal = Padding.mediumPadding)
    ) {
        TextField(
            value = currentDept.department,
            onValueChange = { currentDept.department },
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
            placeholder = { Text(text = "Department") }
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            departmentState.forEach {
                DropdownMenuItem(
                    text = { Text(text = it.department) },
                    onClick = {
                        currentDept = it
                        studentsCompanionViewModel.getBatchByDept(
                            deptId = currentDept.id,
                            dept = currentDept.department
                        )
                        expanded = !expanded
                    }
                )
            }
        }
    }
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(4),
//        horizontalArrangement = Arrangement.spacedBy(Padding.mediumPadding),
//        verticalArrangement = Arrangement.spacedBy(Padding.mediumPadding),
//        contentPadding = PaddingValues(horizontal = Padding.largePadding)
//    ) {
//        items(departmentState.size) {
//            Box(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .clip(MaterialTheme.shapes.medium)
//                    .clickable {
//                        currentDept = departmentState[it]
//                        studentsCompanionViewModel.getBatchByDept(
//                            deptId = currentDept.id,
//                            dept = currentDept.department
//                        )
//                    }
//                    .border(2.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.medium)
//                    .background(if (loginUiState.department.department == departmentState[it].department) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface),
//            ) {
//                Text(
//                    modifier = modifier
//                        .fillMaxWidth()
//                        .padding(Padding.largePadding),
//                    text = departmentState[it].department.uppercase(),
//                    textAlign = TextAlign.Center,
//                    style = MaterialTheme.typography.bodyLarge
//                )
//            }
//        }
//    }
}