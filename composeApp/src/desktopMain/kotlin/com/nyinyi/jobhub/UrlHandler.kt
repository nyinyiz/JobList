package com.nyinyi.jobhub

import com.nyinyi.jobhub.utils.PlatformUrlHandler
import java.awt.Desktop
import java.net.URI

class DesktopUrlHandler : PlatformUrlHandler {
    override fun openUrl(url: String) {
        try {
            val uri = URI(url)
            val desktop = Desktop.getDesktop()
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(uri)
            }
        } catch (e: Exception) {
            println("Failed to open URL: ${e.message}")
        }
    }
}