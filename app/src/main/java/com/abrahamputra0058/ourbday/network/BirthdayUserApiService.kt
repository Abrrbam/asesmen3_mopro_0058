package com.abrahamputra0058.ourbday.network

import com.abrahamputra0058.ourbday.model.BirthdayUser
import com.abrahamputra0058.ourbday.model.OpStatus
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private val BASE_URL = "https://ourbday-api-production.up.railway.app/"

//private val BASE_URL = "https://mpa0097dcdfc840f8471.free.beeceptor.com/"

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
    @GET("birthday-users")
    suspend fun getBirthdayUser(
        @Header("Authorization") userId: String
    ): List<BirthdayUser>

    @Multipart
    @POST("birthday-user")
    suspend fun postBirthdayUser(
        @Header("Authorization") userId: String,
        @Part("nama") nama: RequestBody,
        @Part("tanggalLahir") tanggalLahir: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("birthday-user")
    suspend fun deleteBirthProfile(
        @Header("Authorization") userId: String,
        @Query("id") id: String
    ): OpStatus
}

object BirthdayUserApi {
    val service: BirthdayUserApiService by lazy {
        retrofit.create(BirthdayUserApiService::class.java)
    }

    fun getBirthdayUserPicture(imageId: String): String {
        return "$BASE_URL/public/images/$imageId.jpg"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }