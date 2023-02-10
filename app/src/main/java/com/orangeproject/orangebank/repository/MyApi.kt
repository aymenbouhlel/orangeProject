package com.orangeproject.orangebank.repository

import com.orangeproject.orangebank.repository.models.account.AccountResponse
import retrofit2.http.GET

interface MyApi {

    @GET("/ea42529b-1a24-4f3e-9ba4-8e6665666d6b")
    suspend fun getAllAccount(): AccountResponse
}