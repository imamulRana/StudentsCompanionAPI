package com.imstudio.studentscompanion.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavController
import com.imstudio.studentscompanion.R
import com.imstudio.studentscompanion.StudentsCompanionViewModel
import com.imstudio.studentscompanion.navigation.Screens
import com.imstudio.studentscompanion.ui.components.modifiers.Padding
import com.imstudio.studentscompanion.ui.components.uicomponent.commoncomponent.AboutInfoComponent
import com.imstudio.studentscompanion.ui.components.uicomponent.commoncomponent.HeadingText
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    studentsCompanionViewModel: StudentsCompanionViewModel,
    navController: NavController
) {
    val clipboardManager = LocalClipboardManager.current
    val loginUiState by studentsCompanionViewModel.loginUiState.collectAsState()
    val scrollState = rememberScrollState()
    val text = "imhasanrana@gmail.com"
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val sharedPreferences = LocalContext.current.getSharedPreferences("oka", Context.MODE_PRIVATE)
    Scaffold(snackbarHost = {
        SnackbarHost(
            snackBarHostState,
            snackbar = { snackBarData ->
                Snackbar(
                    snackbarData = snackBarData,
                    modifier = modifier.offset(y = (-Padding.smallPadding)),
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    actionColor = MaterialTheme.colorScheme.onError,
                    contentColor = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.medium
                )
            }
        )
    }) { padding ->
        Column(
            modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(padding)
                .padding(top = Padding.extraLargePadding, bottom = Padding.extraLargePadding),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(Padding.extraHugePadding)) {
                Column(verticalArrangement = Arrangement.spacedBy(Padding.extraLargePadding)) {
                    HeadingText(headingText = "students information")
                    Column(verticalArrangement = Arrangement.spacedBy(Padding.extraLargePadding)) {
                        AboutInfoComponent(
                            title = "Department",
                            description = "${loginUiState.department.department} Department"
                        )
                        AboutInfoComponent(
                            title = "Batch & Section",
                            description = "Batch ${loginUiState.batch.batch.uppercase()} (${loginUiState.section.section.uppercase()}) Section"
                        )
                    }
                }
                Column(verticalArrangement = Arrangement.spacedBy(Padding.extraLargePadding)) {
                    HeadingText(headingText = "schedules information")
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AboutInfoComponent(
                            title = "Routine Version",
                            description = "Make Sure That You Have Internet Connection"
                        )
                        IconButton(
                            modifier = modifier
                                .padding(end = Padding.smallPadding), onClick = {
                                coroutineScope.launch {
                                    snackBarHostState.showSnackbar("Routine Updated")
                                    studentsCompanionViewModel.getAllClass(
                                        loginUiState.section.id,
                                        loginUiState.section.section
                                    )
                                }
                            }) {
                        }
                    }
                }
                Column(verticalArrangement = Arrangement.spacedBy(Padding.extraLargePadding)) {
                    HeadingText(headingText = "app & maintainer information")
                    Column(verticalArrangement = Arrangement.spacedBy(Padding.extraLargePadding)) {
                        AboutInfoComponent(
                            title = "App Version",
                            description = "1.8.12 (Trustworthy)"
                        )
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AboutInfoComponent(
                                title = "Project Maintainer",
                                description = text
                            )
                            IconButton(modifier = modifier
                                .padding(end = Padding.smallPadding),
                                onClick = {
                                    clipboardManager.setText(AnnotatedString(text))
                                    coroutineScope.launch {
                                        snackBarHostState.showSnackbar(
                                            message = "Email Copied"
                                        )
                                    }
                                }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.content_copy),
                                    contentDescription = "Copy Email",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                }
            }
            Button(
                modifier = modifier
                    .padding(horizontal = Padding.largePadding)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(25),
                onClick = {
//                    studentsCompanionViewModel.resetAll()
                    sharedPreferences.edit().clear().apply()

                    navController.navigate(Screens.LoginScreen.route)
                }) {
                Text(
                    modifier = modifier.padding(
                        vertical = Padding.smallPadding
                    ),
                    text = "Logout",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

    }
}