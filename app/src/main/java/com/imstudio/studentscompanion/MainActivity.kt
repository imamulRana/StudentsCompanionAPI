package com.imstudio.studentscompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.imstudio.studentscompanion.repository.StuCompRepo
import com.imstudio.studentscompanion.ui.theme.StudentsCompanionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val studentsCompanionViewModel = StudentsCompanionViewModel(stuCompRepo = StuCompRepo())

        studentsCompanionViewModel.getDeptByInit()

        installSplashScreen().setKeepOnScreenCondition {
            studentsCompanionViewModel.isLoading.value
        }
        setContent {
            val navController = rememberNavController()
            StudentsCompanionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    StudentsCompanionApp(
                        navController = navController,
                        studentsCompanionViewModel = studentsCompanionViewModel
                    )
                }
            }
        }
    }
}

