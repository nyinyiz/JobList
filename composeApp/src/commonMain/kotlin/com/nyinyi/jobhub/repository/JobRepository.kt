package com.nyinyi.jobhub.repository

import com.nyinyi.jobhub.api.JobService
import com.nyinyi.jobhub.response.Job
import kotlinx.coroutines.flow.Flow

class JobRepository(private val jobService: JobService) {
    fun getAllJobs(): Flow<List<Job>> {
        return jobService.fetchJobs()
    }
}