package com.nyinyi.jobhub.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyinyi.jobhub.repository.JobRepository
import com.nyinyi.jobhub.response.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class JobListViewModel(
    private val repository: JobRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<JobListUiState>(JobListUiState.Loading)
    val uiState: StateFlow<JobListUiState> = _uiState.asStateFlow()

    init {
        loadJobs()
    }

    fun loadJobs() {
        viewModelScope.launch {
            _uiState.value = JobListUiState.Loading
            try {
                repository.getAllJobs().collect { jobs ->
                    _uiState.value =
                        JobListUiState.Success(jobs, Clock.System.now().toEpochMilliseconds())
                }
            } catch (e: Exception) {
                _uiState.value = JobListUiState.Error(e.message ?: "Unknown error")
                try {
                    repository.getAllJobs().collect { fallbackJobs ->
                        if (fallbackJobs.isNotEmpty()) {
                            _uiState.value =
                                JobListUiState.Success(
                                    fallbackJobs,
                                    Clock.System.now().toEpochMilliseconds()
                                )
                        }else {
                            _uiState.value = JobListUiState.Error("No data available")
                        }
                    }
                } catch (fallbackException: Exception) {
                    _uiState.value = JobListUiState.Error(fallbackException.message ?: "Unknown error")
                }
            }
        }
    }
}

sealed class JobListUiState {
    data object Loading : JobListUiState()
    data class Success(
        val jobs: List<Job>,
        val lastUpdatedTime: Long = Clock.System.now().toEpochMilliseconds()
    ) : JobListUiState()
    data class Error(val message: String) : JobListUiState()
}