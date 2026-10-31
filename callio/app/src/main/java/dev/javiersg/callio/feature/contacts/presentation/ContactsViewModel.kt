package dev.javiersg.callio.feature.contacts.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.javiersg.callio.feature.contacts.domain.repository.ContactRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.asStateFlow

@OptIn(
    FlowPreview::class,
    ExperimentalCoroutinesApi::class
)
class ContactsViewModel(
    private val contactRepository: ContactRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    val searchQuery = _searchQuery.asStateFlow()

    val uiState = searchQuery
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            val contactsFlow = if (query.isBlank()) {
                contactRepository.observeAll()
            } else {
                contactRepository.search(query)
            }

            contactsFlow.map { contacts ->
                ContactsUiState(
                    contacts = contacts,
                    searchQuery = query
                )
            }
        }
        .onStart {
            emit(ContactsUiState(isLoading = true))
        }
        .catch {
            emit(
                ContactsUiState(
                    errorMessage = "Unable to load contacts."
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ContactsUiState(isLoading = true)
        )

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}