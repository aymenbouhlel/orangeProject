package com.orangeproject.orangebank.business.repository

import com.orangeproject.orangebank.business.models.OrangeAccount
import com.orangeproject.orangebank.business.models.OrangeTransaction

interface OrangeRepositoryI {

    suspend fun getAllAccount(): List<OrangeAccount>
    suspend fun getAllTransaction(url: String): List<OrangeTransaction>

}