package com.orangeproject.orangebank.repository

import com.orangeproject.orangebank.business.models.OrangeAccount
import com.orangeproject.orangebank.business.repository.Repository
import com.orangeproject.orangebank.repository.mapper.OrangeAccountMapper
import javax.inject.Inject

class MyRepository @Inject constructor(private val myApi: MyApi): Repository {
    override suspend fun getAllAccount(): List<OrangeAccount> {
        return   OrangeAccountMapper.mapAllAccount(myApi.getAllAccount())
    }

}