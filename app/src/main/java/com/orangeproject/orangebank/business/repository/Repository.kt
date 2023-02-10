package com.orangeproject.orangebank.business.repository

import com.orangeproject.orangebank.repository.models.account.AccountResponse

interface Repository {

    suspend fun getAllAccount(): AccountResponse

}