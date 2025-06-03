package com.abrahamputra0058.ourbday.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abrahamputra0058.ourbday.network.BirthdayUserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    init {
        retrieveBirthdayUser()
    }

    private fun retrieveBirthdayUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = BirthdayUserApi.service.getBirthdayUser()
                Log.d("MainViewModel", "Success: $result")
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}