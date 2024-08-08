package com.jefftrotz.fitnesstracker.data

import com.jefftrotz.fitnesstracker.model.User
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * API to fetch data stored on the server.
 */
// TODO: Finish
interface API {

    /**
     * Executes a GET request to fetch a user from the server.
     * @return [User] object representing the user that was fetched
     * from the server, or null if the user doesn't exist.
     * @see User
     */
    @GET(value = "user")
    suspend fun getUser(): User?

    /**
     * Executes a POST request to store a [User] on the server.
     */
    @POST(value = "")
    suspend fun postUser()
}