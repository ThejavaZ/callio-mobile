
package dev.javiersg.callio.feature.contacts.data.repository

import dev.javiersg.callio.feature.contacts.data.local.dao.ContactDao
import dev.javiersg.callio.feature.contacts.data.local.entity.ContactEntity
import dev.javiersg.callio.feature.contacts.data.local.entity.ContactSource as EntitySource
import dev.javiersg.callio.feature.contacts.domain.model.Contact
import dev.javiersg.callio.feature.contacts.domain.model.ContactSource
import dev.javiersg.callio.feature.contacts.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineFirstContactRepository(
    private val contactDao: ContactDao
) : ContactRepository {

    override fun observeAll(): Flow<List<Contact>> =
        contactDao.observeAll().map { contacts ->
            contacts.map { it.toDomain() }
        }

    override fun observeById(contactId: Long): Flow<Contact?> =
        contactDao.observeById(contactId).map { contact ->
            contact?.toDomain()
        }

    override fun search(query: String): Flow<List<Contact>> =
        contactDao.search(query.trim()).map { contacts ->
            contacts.map { it.toDomain() }
        }

    override fun observeFavorites(): Flow<List<Contact>> =
        contactDao.observeFavorites().map { contacts ->
            contacts.map { it.toDomain() }
        }

    override fun observeRecent(limit: Int): Flow<List<Contact>> =
        contactDao.observeRecent(limit).map { contacts ->
            contacts.map { it.toDomain() }
        }

    override fun observeCount(): Flow<Int> =
        contactDao.observeCount()

    override fun observeFavoriteCount(): Flow<Int> =
        contactDao.observeFavoriteCount()

    override suspend fun insert(contact: Contact): Long =
        contactDao.insert(contact.toEntity())

    override suspend fun update(contact: Contact) {
        contactDao.update(contact.toEntity().copy(
            updatedAt = System.currentTimeMillis()
        ))
    }

    override suspend fun delete(contact: Contact) {
        contactDao.delete(contact.toEntity())
    }

    override suspend fun updateFavorite(
        contactId: Long,
        isFavorite: Boolean
    ) {
        contactDao.updateFavorite(contactId, isFavorite)
    }

    private fun ContactEntity.toDomain() = Contact(
        id = id,
        firstName = firstName,
        lastName = lastName,
        company = company,
        email = email,
        phoneNumber = phoneNumber,
        notes = notes,
        isFavorite = isFavorite,
        source = ContactSource.valueOf(source.name),
        externalContactId = externalContactId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    private fun Contact.toEntity() = ContactEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        company = company,
        email = email,
        phoneNumber = phoneNumber,
        notes = notes,
        isFavorite = isFavorite,
        source = EntitySource.valueOf(source.name),
        externalContactId = externalContactId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
