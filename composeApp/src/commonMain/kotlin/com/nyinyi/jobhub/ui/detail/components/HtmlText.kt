package com.nyinyi.jobhub.ui.detail.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle


@Composable
fun HtmlText(html: String) {
    Text(
        buildAnnotatedString {
            val regex = Regex("<(\\w+)(?: href=['\"](.*?)['\"])?>(.*?)</\\1>")
            var lastIndex = 0
            for (match in regex.findAll(html)) {
                val start = match.range.first
                if (lastIndex < start) append(html.substring(lastIndex, start))

                val tag = match.groupValues[1]
                val href = match.groupValues[2]
                val content = match.groupValues[3]

                when (tag.lowercase()) {
                    "b" -> withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append(content) }
                    "i" -> withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append(content) }
                    "u" -> withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append(
                            content
                        )
                    }

                    "a" -> {
                        pushStringAnnotation("URL", href)
                        withStyle(
                            SpanStyle(
                                color = Color.Blue,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append(content)
                        }
                        pop()
                    }

                    else -> append(content)
                }

                lastIndex = match.range.last + 1
            }
            if (lastIndex < html.length) append(html.substring(lastIndex))
        },

        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}