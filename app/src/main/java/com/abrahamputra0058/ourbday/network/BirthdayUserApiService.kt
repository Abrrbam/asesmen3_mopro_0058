package com.abrahamputra0058.ourbday.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private val BASE_URL = "https://mpa0097dcdfc840f8471.free.beeceptor.com/"

//GET - /birthday-user
//https://mpa0097dcdfc840f8471.free.beeceptor.com/birthday-user.json

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface BirthdayUserApiService {
    @GET("birthday-user.json")
    suspend fun getBirthdayUser(): String
}

object BirthdayUserApi {
    val service: BirthdayUserApiService by lazy {
        retrofit.create(BirthdayUserApiService::class.java)
    }
}