package com.nyinyi.jobhub.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nyinyi.jobhub.response.Job
import com.nyinyi.jobhub.ui.detail.components.JobDetailContent
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailScreen(
    viewModel: JobDetailViewModel = koinViewModel(),
    jobSlug: String,
    onBackPressed: () -> Unit,
    onShareJob: (Job) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(jobSlug) {
        viewModel.loadJobs(jobSlug)
    }

    when (val state = uiState) {
        is JobDetailUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is JobDetailUiState.Success -> {
            JobDetailContent(
                job = state.job,
                onBackPressed = onBackPressed,
                onShareJob = onShareJob,
                processDescription = { desc -> desc }
            )
        }

        is JobDetailUiState.NotFound -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Job not found", style = MaterialTheme.typography.headlineSmall)
            }
        }

        is JobDetailUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "Error: ${state.message}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )

                    Button(onClick = { viewModel.loadJobs(jobSlug) }) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}





