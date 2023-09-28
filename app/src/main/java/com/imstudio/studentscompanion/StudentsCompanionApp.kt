package com.imstudio.studentscompanion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.imstudio.studentscompanion.navigation.NavigationGraph
import com.imstudio.studentscompanion.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentsCompanionApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    studentsCompanionViewModel: StudentsCompanionViewModel
) {
    val currentDestination by navController.currentBackStackEntryAsState()
    val canNavigateBack = navController.previousBackStackEntry != null
    val currentTitle = when (currentDestination?.destination?.route) {
        Screens.LoginScreen.route -> Screens.LoginScreen.title
        Screens.HomeScreen.route -> Screens.HomeScreen.title
        Screens.ClassSchedules.route -> Screens.ClassSchedules.title
        Screens.BusRouteScreen.route -> Screens.BusRouteScreen.title
        Screens.BusUpSchedules.route -> Screens.BusUpSchedules.title
        Screens.BusDownSchedules.route -> Screens.BusDownSchedules.title
        Screens.ExamSchedules.route -> Screens.ExamSchedules.title
        Screens.SettingsScreen.route -> Screens.SettingsScreen.title
        else -> ""
    }
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = currentTitle,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            navigationIcon = {
                if (canNavigateBack && currentDestination?.destination?.route != Screens.HomeScreen.route) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            modifier = modifier
                                .offset(x = (-1.5).dp),
                            painter = painterResource(id = R.drawable.arrow_back_ios_new),
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = "Go Back"
                        )
                    }
                }
            }, actions = {
                if (currentDestination?.destination?.route != Screens.LoginScreen.route && currentDestination?.destination?.route != Screens.SettingsScreen.route) {
                    IconButton(onClick = { navController.navigate(Screens.SettingsScreen.route) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.more_vert),
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = "View About"
                        )
                    }
                }
            })
    }) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
        ) {
            NavigationGraph(navController, studentsCompanionViewModel)
        }
    }
}