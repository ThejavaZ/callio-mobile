package dev.javiersg.callio.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import dev.javiersg.callio.CallioApplication
import dev.javiersg.callio.feature.home.HomeScreen
import dev.javiersg.callio.feature.contacts.presentation.ContactsViewModel
import dev.javiersg.callio.feature.contacts.presentation.ContactsViewModelFactory
import dev.javiersg.callio.feature.contacts.presentation.screen.ContactsScreen
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
            HomeScreen(
                onOpenContacts = {
                    navController.navigate(CallioDestination.CONTACTS.route)
                },
                onOpenCalls = {
                    navController.navigate(CallioDestination.CALLS.route)
                }
            )
        }

        composable(CallioDestination.CONTACTS.route) {
            val application = androidx.compose.ui.platform.LocalContext.current
                .applicationContext as CallioApplication

            val factory = ContactsViewModelFactory(
                application.container.contactRepository
            )

            val contactsViewModel: ContactsViewModel = viewModel(
                factory = factory
            )

            val uiState by contactsViewModel.uiState.collectAsStateWithLifecycle()
            val searchQuery by contactsViewModel.searchQuery.collectAsStateWithLifecycle()

            ContactsScreen(
                uiState = uiState,
                searchQuery = searchQuery,
                onSearchQueryChanged = contactsViewModel::onSearchQueryChanged
            )
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