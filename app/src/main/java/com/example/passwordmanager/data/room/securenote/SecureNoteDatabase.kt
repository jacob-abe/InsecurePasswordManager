package com.example.passwordmanager.data.room.securenote

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.passwordmanager.model.SecureNote

@Database(entities = [SecureNote::class], exportSchema = false, version = 1)
abstract class SecureNoteDatabase: RoomDatabase() {

    abstract fun secureNoteDao() : ISecureNoteDao

    companion object {

        @Volatile
        private var INSTANCE: SecureNoteDatabase? = null

        fun getDatabaseClient(context: Context) : SecureNoteDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, SecureNoteDatabase::class.java, "SECURE_NOTE_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}