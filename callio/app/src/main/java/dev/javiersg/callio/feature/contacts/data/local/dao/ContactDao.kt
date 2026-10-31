
package dev.javiersg.callio.feature.contacts.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import dev.javiersg.callio.feature.contacts.data.local.entity.ContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Query("""
        SELECT * FROM contacts
        ORDER BY firstName COLLATE NOCASE ASC,
                 lastName COLLATE NOCASE ASC
    """)
    fun observeAll(): Flow<List<ContactEntity>>

    @Query("""
        SELECT * FROM contacts
        WHERE id = :contactId
        LIMIT 1
    """)
    fun observeById(contactId: Long): Flow<ContactEntity?>

    @Query("""
        SELECT * FROM contacts
        WHERE firstName LIKE '%' || :query || '%'
           OR lastName LIKE '%' || :query || '%'
           OR company LIKE '%' || :query || '%'
           OR phoneNumber LIKE '%' || :query || '%'
           OR email LIKE '%' || :query || '%'
        ORDER BY firstName COLLATE NOCASE ASC
    """)
    fun search(query: String): Flow<List<ContactEntity>>

    @Query("""
        SELECT * FROM contacts
        WHERE isFavorite = 1
        ORDER BY firstName COLLATE NOCASE ASC
    """)
    fun observeFavorites(): Flow<List<ContactEntity>>

    @Query("""
        SELECT * FROM contacts
        ORDER BY createdAt DESC
        LIMIT :limit
    """)
    fun observeRecent(limit: Int): Flow<List<ContactEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(contact: ContactEntity): Long

    @Upsert
    suspend fun upsert(contact: ContactEntity)

    @Update
    suspend fun update(contact: ContactEntity)

    @Delete
    suspend fun delete(contact: ContactEntity)

    @Query("""
        UPDATE contacts
        SET isFavorite = :isFavorite,
            updatedAt = :updatedAt
        WHERE id = :contactId
    """)
    suspend fun updateFavorite(
        contactId: Long,
        isFavorite: Boolean,
        updatedAt: Long = System.currentTimeMillis()
    )

    @Query("SELECT COUNT(*) FROM contacts")
    fun observeCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM contacts WHERE isFavorite = 1")
    fun observeFavoriteCount(): Flow<Int>
}
