package com.imstudio.studentscompanion.ui.components.uicomponent.commoncomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.imstudio.studentscompanion.StudentsCompanionViewModel
import com.imstudio.studentscompanion.ui.components.modifiers.Padding

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
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(Padding.mediumPadding),
        verticalArrangement = Arrangement.spacedBy(Padding.mediumPadding),
        contentPadding = PaddingValues(horizontal = Padding.largePadding)
    ) {
        items(departmentState.size) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .clickable {
                        currentDept = departmentState[it]
                        studentsCompanionViewModel.getBatchByDept(
                            deptId = currentDept.id,
                            dept = currentDept.department
                        )
                    }
                    .border(2.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.medium)
                    .background(if (loginUiState.department.department == departmentState[it].department) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface),
            ) {
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(Padding.largePadding),
                    text = departmentState[it].department.uppercase(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}