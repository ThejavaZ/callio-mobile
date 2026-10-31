package dev.javiersg.callio.feature.contacts.presentation

import dev.javiersg.callio.feature.contacts.domain.model.Contact

data class ContactsUiState(
    val contacts: List<Contact> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)