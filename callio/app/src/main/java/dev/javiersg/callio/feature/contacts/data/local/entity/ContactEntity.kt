
package dev.javiersg.callio.feature.contacts.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

enum class ContactSource {
    MANUAL,
    ANDROID
}

@Entity(
    tableName = "contacts",
    indices = [
        Index(value = ["externalContactId"], unique = true),
        Index(value = ["firstName", "lastName"])
    ]
)
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val firstName: String,
    val lastName: String = "",

    val company: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val notes: String? = null,

    val isFavorite: Boolean = false,

    val source: ContactSource = ContactSource.MANUAL,

    // ID del contacto original en Android Contacts Provider.
    val externalContactId: String? = null,

    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
