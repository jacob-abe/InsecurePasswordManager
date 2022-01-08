package com.example.passwordmanager.data.room.securenote

import androidx.room.*
import com.example.passwordmanager.model.SecureNote

@Dao
interface ISecureNoteDao {

    @Query("SELECT * FROM securenote WHERE id == :id")
    suspend fun getSecureNote(id: Int): SecureNote

    @Query("SELECT * FROM securenote")
    suspend fun getSecureNotes(): List<SecureNote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setSecureNote(secureNote: SecureNote)

    @Delete
    suspend fun deleteSecureNote(secureNote: SecureNote)
}