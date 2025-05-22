package com.nyinyi.jobhub.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyinyi.jobhub.repository.JobRepository
import com.nyinyi.jobhub.response.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JobDetailViewModel(
    private val repository: JobRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<JobDetailUiState>(JobDetailUiState.Loading)
    val uiState: StateFlow<JobDetailUiState> = _uiState.asStateFlow()

    fun loadJobs(slug: String) {
        viewModelScope.launch {
            _uiState.value = JobDetailUiState.Loading
            try {
                repository.getAllJobs().collect { jobs ->
                    val filteredJob = jobs.find { it.slug == slug }
                    if (filteredJob != null) {
                        _uiState.value = JobDetailUiState.Success(filteredJob)
                    } else {
                        _uiState.value = JobDetailUiState.NotFound
                    }
                }
            } catch (e: Exception) {
                _uiState.value = JobDetailUiState.Error(e.message ?: "Unknown error")
                try {
                    repository.getAllJobs().collect { fallbackJobs ->
                        val filteredJob = fallbackJobs.find { it.slug == slug }
                        if (filteredJob != null) {
                            _uiState.value = JobDetailUiState.Success(filteredJob)
                        } else {
                            _uiState.value = JobDetailUiState.NotFound
                        }
                    }
                } catch (fallbackException: Exception) {
                    _uiState.value =
                        JobDetailUiState.Error(fallbackException.message ?: "Unknown error")
                }
            }
        }
    }

    fun processDescription(rawDescription: String): String {
        return rawDescription
            .replace(Regex("<br\\s*/?>|<p>|</p>"), "\n\n")
            .replace(Regex("<.*?>"), "")
            .trim()
    }

}

sealed class JobDetailUiState {
    data object Loading : JobDetailUiState()
    data class Success(val job: Job) : JobDetailUiState()
    data object NotFound : JobDetailUiState()
    data class Error(val message: String) : JobDetailUiState()
}