package dev.javiersg.callio.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.javiersg.callio.core.database.converter.ContactConverters
import dev.javiersg.callio.feature.contacts.data.local.dao.ContactDao
import dev.javiersg.callio.feature.contacts.data.local.entity.ContactEntity

@Database(
    entities = [ContactEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(ContactConverters::class)
abstract class CallioDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var INSTANCE: CallioDatabase? = null

        fun getDatabase(context: Context): CallioDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    CallioDatabase::class.java,
                    "callio_database"
                ).build().also { database ->
                    INSTANCE = database
                }
            }
        }
    }
}
