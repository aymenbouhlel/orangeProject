package com.orangeproject.orangebank.repository

import com.orangeproject.orangebank.business.repository.Repository
import com.orangeproject.orangebank.repository.models.account.AccountResponse
import javax.inject.Inject

class MyRepository @Inject constructor(private val myApi: MyApi): Repository {
    override suspend fun getAllAccount(): AccountResponse {
        return myApi.getAllAccount()
    }

}