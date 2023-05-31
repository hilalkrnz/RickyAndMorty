package com.example.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.repository.DataStorePreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStorePreferenceRepository: DataStorePreferenceRepository
) : ViewModel() {

    private val _currentUser = MutableLiveData(false)
    val currentUser: LiveData<Boolean> get() = _currentUser


    fun getCurrentUser() {
        viewModelScope.launch {
            dataStorePreferenceRepository.getCurrentUser.collect {
                _currentUser.postValue(it)
            }
        }
    }

    fun setCurrentUser(currentUser: Boolean) {
        viewModelScope.launch {
            dataStorePreferenceRepository.setCurrentUser(currentUser)
        }
    }

}