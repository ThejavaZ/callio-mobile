package dev.javiersg.callio.feature.contacts.domain.model

enum class ContactSource {
    MANUAL,
    ANDROID
}

data class Contact(
    val id: Long = 0,
    val firstName: String,
    val lastName: String = "",
    val company: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val notes: String? = null,
    val isFavorite: Boolean = false,
    val source: ContactSource = ContactSource.MANUAL,
    val externalContactId: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    val fullName: String
        get() = listOf(firstName, lastName)
            .filter(String::isNotBlank)
            .joinToString(" ")
}
