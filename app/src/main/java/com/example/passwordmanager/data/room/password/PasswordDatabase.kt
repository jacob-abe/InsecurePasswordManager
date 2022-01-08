package com.example.passwordmanager.data.room.password

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.passwordmanager.model.Password

@Database(entities = [Password::class], version = 1, exportSchema = false)
abstract class PasswordDatabase: RoomDatabase() {

    abstract fun passwordDao() : IPasswordDao

    companion object {

        @Volatile
        private var INSTANCE: PasswordDatabase? = null

        fun getDatabaseClient(context: Context) : PasswordDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, PasswordDatabase::class.java, "PASSWORD_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }
}