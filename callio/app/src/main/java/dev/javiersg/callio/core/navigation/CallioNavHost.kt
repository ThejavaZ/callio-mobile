package dev.javiersg.callio.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import dev.javiersg.callio.feature.home.HomeScreen
import dev.javiersg.callio.feature.contacts.ContactsScreen
import dev.javiersg.callio.feature.calls.CallsScreen
import dev.javiersg.callio.feature.favorites.FavoritesScreen
import dev.javiersg.callio.feature.settings.SettingsScreen

@Composable
fun CallioNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = CallioDestination.HOME.route,
        modifier = modifier
    ) {
        composable(CallioDestination.HOME.route) {
            HomeScreen()
        }

        composable(CallioDestination.CONTACTS.route) {
            ContactsScreen()
        }

        composable(CallioDestination.CALLS.route) {
            CallsScreen()
        }

        composable(CallioDestination.FAVORITES.route) {
            FavoritesScreen()
        }

        composable(CallioDestination.SETTINGS.route) {
            SettingsScreen()
        }
    }
}