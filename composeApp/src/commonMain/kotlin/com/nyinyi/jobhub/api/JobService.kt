package com.nyinyi.jobhub.api

import com.nyinyi.jobhub.response.ApiResponse
import com.nyinyi.jobhub.response.Job
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class JobService(private val client: HttpClient) {

    private val BASE_URL = "https://www.arbeitnow.com/api/job-board-api"

    fun fetchJobs(): Flow<List<Job>> = flow {
        try {
            val response: ApiResponse = client.get(BASE_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }.body()
            emit(response.data)
        } catch (e: Exception) {
            println("Error fetching jobs flow: ${e.message}")
            emit(Job.getSampleJobs())
        }
    }
}