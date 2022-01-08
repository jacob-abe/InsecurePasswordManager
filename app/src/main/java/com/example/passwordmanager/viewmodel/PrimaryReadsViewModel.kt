package com.example.passwordmanager.viewmodel

import androidx.lifecycle.*
import com.example.passwordmanager.model.Password
import com.example.passwordmanager.model.Result
import com.example.passwordmanager.model.SecureNote
import com.example.passwordmanager.repository.PasswordRepository
import com.example.passwordmanager.repository.SecureNoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrimaryReadsViewModel @Inject constructor(
    private val passwordRepository: PasswordRepository,
    private val secureNoteRepository: SecureNoteRepository): ViewModel() {

    var passwordList : LiveData<Result<List<Password>>?> = MutableLiveData(null)
    var secureNoteList: LiveData<Result<List<SecureNote>>?> = MutableLiveData(null)

    init{
        viewModelScope.launch {
            getData()
        }
    }

    private suspend fun getData(){
        passwordList = passwordRepository.getPasswordList().asLiveData(viewModelScope.coroutineContext)
        secureNoteList = secureNoteRepository.getSecureNoteList().asLiveData(viewModelScope.coroutineContext)
    }
}