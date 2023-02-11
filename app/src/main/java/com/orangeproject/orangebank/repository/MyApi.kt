package com.orangeproject.orangebank.repository

import com.orangeproject.orangebank.repository.models.account.AccountResponse
import com.orangeproject.orangebank.repository.models.transaction.TransactionResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface MyApi {

    @GET("/v3/ea42529b-1a24-4f3e-9ba4-8e6665666d6b")
    suspend fun getAllAccount(): AccountResponse

    @GET
    suspend fun getTransaction(@Url url: String): TransactionResponse
}