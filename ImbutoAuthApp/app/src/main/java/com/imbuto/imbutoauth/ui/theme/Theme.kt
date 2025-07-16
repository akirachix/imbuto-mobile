
package com.example.app.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.imbuto.imbutoauth.ui.theme.Typography

import androidx.compose.ui.Modifier
//import com.imbuto.imbutoauth.AuthNavHost
import com.imbuto.imbutoauth.ui.theme.md_theme_light_background
import com.imbuto.imbutoauth.ui.theme.md_theme_light_onBackground
import com.imbuto.imbutoauth.ui.theme.md_theme_light_onPrimary
import com.imbuto.imbutoauth.ui.theme.md_theme_light_outline
import com.imbuto.imbutoauth.ui.theme.md_theme_light_primary
import androidx.compose. material3.Typography

private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    outline = md_theme_light_outline,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    // ... add other colors if you want
)

private val DarkColors = darkColorScheme(
    // You can provide dark color variants here or fallback
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    outline = md_theme_light_outline,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
)

@Composable
fun ImbutoAuthAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

