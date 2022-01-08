package com.example.passwordmanager.data.room.password

import androidx.room.*
import com.example.passwordmanager.model.Password

@Dao
interface IPasswordDao {

    @Query("SELECT * FROM password WHERE id == :id")
    suspend fun getPassword(id: Int): Password

    @Query("SELECT * FROM password")
    suspend fun getPasswords(): List<Password>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setPassword(password: Password)

    @Delete
    suspend fun deletePassword(password: Password)
}