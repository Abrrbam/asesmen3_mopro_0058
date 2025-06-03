package com.abrahamputra0058.ourbday.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abrahamputra0058.ourbday.model.BirthdayUser
import com.abrahamputra0058.ourbday.network.ApiStatus
import com.abrahamputra0058.ourbday.network.BirthdayUserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<BirthdayUser>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set

    init {
        retrieveBirthdayUser()
    }

    private fun retrieveBirthdayUser() {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING
            try {
                data.value = BirthdayUserApi.service.getBirthdayUser()
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}