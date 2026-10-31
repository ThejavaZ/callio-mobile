package dev.javiersg.callio

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

import dev.javiersg.callio.core.navigation.CallioBottomBar
import dev.javiersg.callio.core.navigation.CallioNavHost

@Composable
fun CallioApp() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            CallioBottomBar(navController)
        }
    ) { innerPadding ->

        CallioNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}