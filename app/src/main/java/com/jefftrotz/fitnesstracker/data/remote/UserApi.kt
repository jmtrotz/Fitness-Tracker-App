package com.jefftrotz.fitnesstracker.data.remote

import com.jefftrotz.fitnesstracker.model.User
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

// TODO: Finish GET and POST requests
@Singleton
interface UserApi {
    @GET(value = "user")
    suspend fun getUser(): User

    @POST(value = "")
    suspend fun postUser()
}