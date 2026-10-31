package dev.javiersg.callio.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import java.util.Calendar
import dev.javiersg.callio.core.designsystem.components.CallioEmptyState
import dev.javiersg.callio.core.designsystem.components.CallioSectionHeader
import dev.javiersg.callio.core.designsystem.components.CallioSummaryCard

@Composable
fun HomeScreen(
    onOpenContacts: () -> Unit,
    onOpenCalls: () -> Unit
) {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    val greeting = when (currentHour) {
        in 5..11 -> "Good morning"
        in 12..17 -> "Good afternoon"
        else -> "Good evening"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Header
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = greeting,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Here's your activity at a glance.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Summary
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            CallioSectionHeader(title = "Overview")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CallioSummaryCard(
                    title = "Contacts",
                    value = "0",
                    icon = Icons.Outlined.People,
                    modifier = Modifier.weight(1f)
                )

                CallioSummaryCard(
                    title = "Calls",
                    value = "0",
                    icon = Icons.Outlined.Call,
                    modifier = Modifier.weight(1f)
                )

                CallioSummaryCard(
                    title = "Favorites",
                    value = "0",
                    icon = Icons.Outlined.Star,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Quick actions
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            CallioSectionHeader(title = "Quick actions")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                HomeActionCard(
                    title = "Contacts",
                    description = "Manage your contacts",
                    icon = Icons.Outlined.PersonAdd,
                    onClick = onOpenContacts,
                    modifier = Modifier.weight(1f)
                )

                HomeActionCard(
                    title = "Calls",
                    description = "View your activity",
                    icon = Icons.Outlined.Call,
                    onClick = onOpenCalls,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Recent contacts
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            CallioSectionHeader(
                title = "Recent contacts",
                actionLabel = "See all",
                onActionClick = onOpenContacts
            )

            CallioEmptyState(
                icon = Icons.Outlined.People,
                title = "No recent contacts",
                description = "Your recently added contacts will appear here."
            )
        }
    }
}

@Composable
private fun HomeActionCard(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
