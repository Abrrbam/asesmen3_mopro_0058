package com.abrahamputra0058.ourbday.ui.screen

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abrahamputra0058.ourbday.model.BirthdayUser
import com.abrahamputra0058.ourbday.model.User
import com.abrahamputra0058.ourbday.network.ApiStatus
import com.abrahamputra0058.ourbday.network.BirthdayUserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<BirthdayUser>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    init {
        retrieveBirthdayUser()
    }

    fun retrieveBirthdayUser() {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING
            try {
                data.value = BirthdayUserApi.service.getBirthdayUser()
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                status.value = ApiStatus.FAILED
            }
        }
    }

    fun saveBirthdayUser(userId: String, nama: String, tanggalLahir: String, bitmap: Bitmap) {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val result = BirthdayUserApi.service.postBirthdayUser(
                    userId,
                    nama.toRequestBody("text/plain".toMediaTypeOrNull()),
                    tanggalLahir.toRequestBody("text/plain".toMediaTypeOrNull()),
                    bitmap.toMultipartBody()
                )

                if (result.status == "success")
                    retrieveBirthdayUser()
                else
                    throw Exception(result.message)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    private fun Bitmap.toMultipartBody(): MultipartBody.Part {
        val stream = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()
        val requestBody = byteArray.toRequestBody(
            "image/jpeg".toMediaTypeOrNull(), 0, byteArray.size
        )
        return MultipartBody.Part.createFormData(
            "image", "image.jpg", requestBody
        )
    }

    fun clearMessage() { errorMessage.value = null }
}