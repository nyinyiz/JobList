package com.nyinyi.jobhub.di

import com.nyinyi.jobhub.api.JobService
import com.nyinyi.jobhub.repository.JobRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val appModule = module {
    // Network
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
        }
    }

    // API Services
    single { JobService(get()) }

    // Repositories
    single { JobRepository(get()) }
}