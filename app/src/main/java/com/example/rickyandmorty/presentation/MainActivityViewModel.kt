package com.example.rickyandmorty.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmorty.data.repository.DataStorePreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(): ViewModel() {

    private val _isBottomBarVisible = MutableLiveData(false)
    val isBottomBarVisible: LiveData<Boolean> get() = _isBottomBarVisible

//    private val _isLoading = MutableLiveData(true)
//    val isLoading : LiveData<Boolean> get() = _isLoading
//
//    init {
//        viewModelScope.launch {
//            delay(3000)
//            _isLoading.value = false
//        }
//    }


    fun setBottomBarVisibility(isVisible: Boolean) {
        _isBottomBarVisible.value = isVisible
    }

}