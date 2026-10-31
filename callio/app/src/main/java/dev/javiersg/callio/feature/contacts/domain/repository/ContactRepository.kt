
package dev.javiersg.callio.feature.contacts.domain.repository

import dev.javiersg.callio.feature.contacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    fun observeAll(): Flow<List<Contact>>

    fun observeById(contactId: Long): Flow<Contact?>

    fun search(query: String): Flow<List<Contact>>

    fun observeFavorites(): Flow<List<Contact>>

    fun observeRecent(limit: Int = 5): Flow<List<Contact>>

    fun observeCount(): Flow<Int>

    fun observeFavoriteCount(): Flow<Int>

    suspend fun insert(contact: Contact): Long

    suspend fun update(contact: Contact)

    suspend fun delete(contact: Contact)

    suspend fun updateFavorite(
        contactId: Long,
        isFavorite: Boolean
    )
}
