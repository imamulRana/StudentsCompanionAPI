package com.imstudio.studentscompanion.navigation

sealed class Screens(val route: String, val title: String) {
    object LoginScreen : Screens("login_screen", "")
    object HomeScreen : Screens("home_screen", "Students Companion")
    object ClassSchedules : Screens("class_schedules", "Class Schedules")
    object BusRouteScreen : Screens("bus_routes", "Bus Routes")
    object BusUpSchedules : Screens("up_routes", "Up Routes")
    object BusDownSchedules : Screens("down_routes", "Down Routes")
    object ExamSchedules : Screens("exam_schedules", "Exam Schedules")
    object SettingsScreen : Screens("settings_screen", "About")
}
