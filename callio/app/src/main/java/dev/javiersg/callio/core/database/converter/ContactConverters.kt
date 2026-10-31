
package dev.javiersg.callio.core.database.converter

import androidx.room.TypeConverter
import dev.javiersg.callio.feature.contacts.data.local.entity.ContactSource

class ContactConverters {

    @TypeConverter
    fun fromContactSource(source: ContactSource): String {
        return source.name
    }

    @TypeConverter
    fun toContactSource(value: String): ContactSource {
        return ContactSource.valueOf(value)
    }
}
