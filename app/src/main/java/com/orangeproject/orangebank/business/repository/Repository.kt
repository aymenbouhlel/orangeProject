package com.orangeproject.orangebank.business.repository

import com.orangeproject.orangebank.business.models.OrangeAccount

interface Repository {

    suspend fun getAllAccount(): List<OrangeAccount>

}