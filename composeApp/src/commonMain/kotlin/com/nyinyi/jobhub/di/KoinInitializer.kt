package com.nyinyi.jobhub.di

import org.koin.core.context.startKoin

object KoinInitializer {
    fun init() {
        startKoin {
            modules(appModule)
        }
    }
}