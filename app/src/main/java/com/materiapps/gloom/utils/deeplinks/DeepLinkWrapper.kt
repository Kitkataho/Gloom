package com.materiapps.gloom.utils.deeplinks

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

typealias DeepLinkContent = @Composable (DeepLinkHandler) -> Unit

@Composable
fun ComponentActivity.DeepLinkWrapper(
    content: DeepLinkContent
) {
    val handler = DeepLinkHandler()

    CompositionLocalProvider(LocalDeepLinkHandler provides handler) {
        content(handler).also {
            handler.handle(intent)
            addOnNewIntentListener {
                handler.handle(it)
            }
        }
    }
}