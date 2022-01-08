package com.example.passwordmanager.repository

import com.example.passwordmanager.model.Result
import com.example.passwordmanager.data.room.password.IPasswordDao
import com.example.passwordmanager.model.Password
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
class PasswordRepository
@Inject constructor(
    //Can pass remote source here
    private val passwordDao: IPasswordDao
) {
    suspend fun getPasswordList(): Flow<Result<List<Password>>?> {
        return flow {
            emit(fetchAllPasswordsFromLocal())
            //Emit loading and then call from remote then emit that when needed
        }.flowOn(Dispatchers.IO)
    }

    suspend fun setPassword(password: Password): Flow<Result<Int>?> {
        return flow {
            emit(setPasswordToLocal(password))
            //Emit loading and then call from remote then emit that when needed
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun fetchAllPasswordsFromLocal(): Result<List<Password>> =
        passwordDao.getPasswords().let {
            Result.success(it)
        }

    private suspend fun setPasswordToLocal(password: Password): Result<Int> =
        passwordDao.setPassword(password).let {
            Result.success(1)
        }
}