package com.nyinyi.jobhub

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.nyinyi.jobhub.utils.PlatformUrlHandler

class UrlHandler(private val context: Context) : PlatformUrlHandler {
    override fun openUrl(url: String) {
        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(browserIntent)
    }
}