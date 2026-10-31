package dev.javiersg.callio.core.di

import android.content.Context
import dev.javiersg.callio.core.database.CallioDatabase
import dev.javiersg.callio.feature.contacts.data.repository.OfflineFirstContactRepository
import dev.javiersg.callio.feature.contacts.domain.repository.ContactRepository

class CallioContainer(context: Context) {

    private val database = CallioDatabase.getDatabase(context)

    val contactRepository: ContactRepository by lazy {
        OfflineFirstContactRepository(
            contactDao = database.contactDao()
        )
    }
}