package com.orangeproject.orangebank.repository.mapper

import com.orangeproject.orangebank.business.models.OrangeAccount
import com.orangeproject.orangebank.repository.models.account.Account
import com.orangeproject.orangebank.repository.models.account.AccountResponse

object OrangeAccountMapper {

    fun mapAllAccount(accountResponse: AccountResponse): List<OrangeAccount> =
        accountResponse.Data.Account.map {
            mapAccount(it)
        }
    private fun mapAccount(account: Account?) = OrangeAccount(
        accountId = account?.accountId,
        nickname = account?.nickname,
        status = account?.status,
        transactionsUrl = account?.transactionsUrl
    )
}

