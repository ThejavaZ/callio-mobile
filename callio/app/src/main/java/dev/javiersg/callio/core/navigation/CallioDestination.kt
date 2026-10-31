package dev.javiersg.callio.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class CallioDestination(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    HOME(
        route = "home",
        label = "Home",
        icon = Icons.Default.Home
    ),
    CONTACTS(
        route = "contacts",
        label = "Contacts",
        icon = Icons.Default.People
    ),
    CALLS(
        route = "calls",
        label = "Calls",
        icon = Icons.Default.Call
    ),
    FAVORITES(
        route = "favorites",
        label = "Favorites",
        icon = Icons.Default.Star
    ),
    SETTINGS(
        route = "settings",
        label = "Settings",
        icon = Icons.Default.Settings
    )
}