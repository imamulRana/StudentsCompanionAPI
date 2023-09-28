package com.imstudio.studentscompanion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.imstudio.studentscompanion.StudentsCompanionViewModel
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
    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route
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