package com.nyinyi.jobhub

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nyinyi.jobhub.di.KoinInitializer
import com.nyinyi.jobhub.navigation.route.Routes
import com.nyinyi.jobhub.ui.list.JobListScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

// Initialize Koin when the app starts
private val koinInitializer = KoinInitializer.init()

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Routes.JobList
        ) {
            composable<Routes.JobList>(
                exitTransition = { slideOutHorizontally() },
                popEnterTransition = { slideInHorizontally() }
            ) {
                JobListScreen(onJobClick = { job ->

                })
            }
        }
    }
}