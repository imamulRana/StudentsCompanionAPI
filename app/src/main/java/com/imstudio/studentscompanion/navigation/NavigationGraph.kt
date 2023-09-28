package com.imstudio.studentscompanion.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.imstudio.studentscompanion.StudentsCompanionViewModel
import com.imstudio.studentscompanion.model.Batch
import com.imstudio.studentscompanion.model.Department
import com.imstudio.studentscompanion.model.LoginUiState
import com.imstudio.studentscompanion.model.Section
import com.imstudio.studentscompanion.screens.BusDownSchedules
import com.imstudio.studentscompanion.screens.BusRouteScreen
import com.imstudio.studentscompanion.screens.BusUpSchedules
import com.imstudio.studentscompanion.screens.ClassSchedules
import com.imstudio.studentscompanion.screens.ExamSchedules
import com.imstudio.studentscompanion.screens.HomeScreen
import com.imstudio.studentscompanion.screens.LoginScreen
import com.imstudio.studentscompanion.screens.SettingsScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    studentsCompanionViewModel: StudentsCompanionViewModel
) {
    val loginUiState by studentsCompanionViewModel.loginUiState.collectAsState()
    val sharedPreferences = LocalContext.current.getSharedPreferences("oka", Context.MODE_PRIVATE)

    if (sharedPreferences.getInt(
            "dept",
            0
        ) != 0 && loginUiState.department.id == 0
    ) {
        val uiState = LoginUiState(
            department = Department(
                sharedPreferences.getInt("dept", 0),
                sharedPreferences.getString("deptS", "")!!
            ),
            batch = Batch(
                sharedPreferences.getInt("batc", 0),
                sharedPreferences.getString("batcS", "")!!
            ),
            section = Section(
                sharedPreferences.getInt("sect", 0),
                sharedPreferences.getString("sectS", "")!!
            )
        )
        studentsCompanionViewModel.updateLoginUiState(uiState)
        studentsCompanionViewModel.getAllClass(uiState.section.id, uiState.section.section)
    }

    NavHost(
        navController = navController,
        startDestination =
        if (sharedPreferences.getInt(
                "dept",
                0
            ) == 0
        ) Screens.LoginScreen.route else Screens.HomeScreen.route
    ) {
        composable(Screens.LoginScreen.route) {
            LoginScreen(
                studentsCompanionViewModel = studentsCompanionViewModel,
                onNextNavigate = {
                    navController.navigate(Screens.HomeScreen.route)
                })
        }
        composable(Screens.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                studentsCompanionViewModel = studentsCompanionViewModel
            )
        }
        composable(Screens.ClassSchedules.route) {
            ClassSchedules(studentsCompanionViewModel = studentsCompanionViewModel)
        }
        composable(Screens.ExamSchedules.route) {
            ExamSchedules()
        }
        composable(Screens.BusRouteScreen.route) {
            BusRouteScreen(
                navController = navController,
            )
        }
        composable(Screens.BusUpSchedules.route) {
            BusUpSchedules(studentsCompanionViewModel = studentsCompanionViewModel)
        }
        composable(Screens.BusDownSchedules.route) {
            BusDownSchedules(studentsCompanionViewModel = studentsCompanionViewModel)
        }
        composable(Screens.SettingsScreen.route) {
            SettingsScreen(
                studentsCompanionViewModel = studentsCompanionViewModel,
                navController = navController
            )
        }
    }
}