package com.example.passwordmanager.di

import android.content.Context
import androidx.room.Room
import com.example.passwordmanager.data.room.password.IPasswordDao
import com.example.passwordmanager.data.room.password.PasswordDatabase
import com.example.passwordmanager.data.room.securenote.ISecureNoteDao
import com.example.passwordmanager.data.room.securenote.SecureNoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun providePasswordDatabase(@ApplicationContext appContext: Context): PasswordDatabase {
        return Room.databaseBuilder(
            appContext,
            PasswordDatabase::class.java,
            "PASSWORD_DATABASE"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSecureNoteDatabase(@ApplicationContext appContext: Context): SecureNoteDatabase {
        return Room.databaseBuilder(
            appContext,
            SecureNoteDatabase::class.java,
            "SECURE_NOTE_DATABASE"
        ).build()
    }

    @Provides
    fun providePasswordDao(passwordDatabase: PasswordDatabase): IPasswordDao {
        return passwordDatabase.passwordDao()
    }

    @Provides
    fun provideSecureNoteDao(secureNoteDatabase: SecureNoteDatabase): ISecureNoteDao {
        return secureNoteDatabase.secureNoteDao()
    }
}