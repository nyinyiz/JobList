package com.nyinyi.jobhub.ui.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nyinyi.jobhub.response.Job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailContent(
    job: Job,
    onBackPressed: () -> Unit,
    onShareJob: (Job) -> Unit,
    processDescription: (String) -> String
) {
//    val urlLauncher = remember { UrlLauncher.create() }
    val scrollState = rememberScrollState()

    // Process the job description
    val htmlDescription = remember(job.description) {
        processDescription(job.description)
    }

    // Animation states
    var isContentVisible by remember { mutableStateOf(false) }
    val headerHeightPx = 280.dp
    val scrollProgress = (scrollState.value / 500f).coerceIn(0f, 1f)
    val headerScale = 1f - (0.25f * scrollProgress)
    val headerAlpha = 1f - (0.5f * scrollProgress)

    // Trigger content visibility animation after a short delay
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(150)
        isContentVisible = true
    }

    Scaffold { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            // Background gradient
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.surface,
                                MaterialTheme.colorScheme.surface,
                                MaterialTheme.colorScheme.background,
                            )
                        )
                    )
            )

            // Main content
            Column(modifier = Modifier.fillMaxSize()) {
                // Header with company info
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(headerHeightPx)
                        .graphicsLayer {
                            alpha = headerAlpha
                            scaleX = headerScale
                            scaleY = headerScale
                        }
                ) {
                    // Background gradient
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.primary,
                                        MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f)
                                    )
                                )
                            )
                    )

                    // Company info
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp, vertical = 48.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .shadow(
                                    elevation = 8.dp,
                                    shape = CircleShape,
                                    spotColor = MaterialTheme.colorScheme.primary
                                )
                                .clip(CircleShape)
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(
                                            MaterialTheme.colorScheme.onPrimary,
                                            MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f)
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = job.companyName.first().toString().uppercase(),
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = job.companyName,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            text = job.title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    // Back button
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.TopStart)
                    ) {
                        FilledIconButton(
                            onClick = onBackPressed,
                            modifier = Modifier
                                .size(48.dp)
                                .shadow(4.dp, CircleShape),
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                            )
                        ) {
                            Text(
                                "←",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    // Share button
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.TopEnd)
                    ) {
                        FilledIconButton(
                            onClick = { onShareJob(job) },
                            modifier = Modifier
                                .size(48.dp)
                                .shadow(4.dp, CircleShape),
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                            )
                        ) {
                            Text(
                                "↗",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f)) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        // Quick Info Row
                        JobInfoChips(job)

                        // Tags section
                        if (job.tags.isNotEmpty() || job.jobTypes.isNotEmpty()) {
                            JobTagsCard(job)
                        }

                        // Description section
                        JobDescriptionCard(htmlDescription)

                        // Apply button
                        ElevatedButton(
                            onClick = { /*urlLauncher.openUrl(job.url)*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                                .height(56.dp),
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            elevation = ButtonDefaults.elevatedButtonElevation(
                                defaultElevation = 6.dp,
                                pressedElevation = 8.dp
                            )
                        ) {
                            Text(
                                "Apply for Position",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Bottom spacing
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }
        }
    }
}