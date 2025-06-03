package com.abrahamputra0058.ourbday.network

import com.abrahamputra0058.ourbday.model.BirthdayUser
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val BASE_URL = "https://mpa0097dcdfc840f8471.free.beeceptor.com/"

//GET - /birthday-user
//https://mpa0097dcdfc840f8471.free.beeceptor.com/birthday-user.json

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BirthdayUserApiService {
    @GET("birthday-user.json")
    suspend fun getBirthdayUser(): List<BirthdayUser>
}

object BirthdayUserApi {
    val service: BirthdayUserApiService by lazy {
        retrofit.create(BirthdayUserApiService::class.java)
    }

    fun getBirthdayUserPicture(imageId: String): String {
        return "$BASE_URL$imageId.jpg"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }