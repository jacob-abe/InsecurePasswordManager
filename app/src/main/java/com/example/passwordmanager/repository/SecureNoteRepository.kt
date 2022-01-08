package com.example.passwordmanager.repository

import com.example.passwordmanager.data.room.securenote.ISecureNoteDao
import com.example.passwordmanager.model.Result
import com.example.passwordmanager.model.SecureNote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class SecureNoteRepository @Inject constructor(
    //Can pass remote source here
    private val secureNoteDao: ISecureNoteDao
) {

    suspend fun getSecureNoteList(): Flow<Result<List<SecureNote>>?> {
        return flow {
            emit(getAllSecureNotesFromLocal())
            //Emit loading and then call from remote then emit that when needed
        }.flowOn(Dispatchers.IO)
    }

    suspend fun setSecureNote(secureNote: SecureNote): Flow<Result<Int>?> {
        return flow {
            emit(setSecureNoteToLocal(secureNote))
            //Emit loading and then call from remote then emit that when needed
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getAllSecureNotesFromLocal(): Result<List<SecureNote>> =
        secureNoteDao.getSecureNotes().let {
            Result.success(it)
        }

    private suspend fun setSecureNoteToLocal(secureNote: SecureNote): Result<Int>  =
        secureNoteDao.setSecureNote(secureNote).let {
            Result.success(1)
        }

    //Define methods for remote call here
}