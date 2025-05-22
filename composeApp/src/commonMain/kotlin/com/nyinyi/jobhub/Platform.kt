package com.nyinyi.jobhub

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform