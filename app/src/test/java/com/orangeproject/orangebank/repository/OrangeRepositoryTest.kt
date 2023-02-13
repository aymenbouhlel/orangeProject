package com.orangeproject.orangebank.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.orangeproject.orangebank.business.models.OrangeAccount
import com.orangeproject.orangebank.repository.models.account.Account
import com.orangeproject.orangebank.repository.models.account.AccountResponse
import com.orangeproject.orangebank.repository.models.account.Data
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class OrangeRepositoryTest {

    private val orangeApi: OrangeApi = mock()
    private val orangeRepository = OrangeRepository(orangeApi)

    @Test
    fun `test getAllAccount success`() {
        val expectedAccounts = listOf(
            OrangeAccount(
                accountId = "123",
                nickname = "Aymen",
                status = "active",
                transactionsUrl = "www.transactions.com"
            )
        )

        runBlocking {
            whenever(orangeApi.getAllAccount()).thenReturn(
                AccountResponse(
                    Data(
                        listOf(
                            Account(
                                account = emptyList(),
                                accountId = "123",
                                accountSubType = "test",
                                accountType = "test",
                                currency = "test",
                                nickname = "Aymen",
                                openingDate = "",
                                status = "active",
                                statusUpdateDateTime = "test",
                                transactionsUrl = "www.transactions.com"
                            )
                        )
                    )
                )
            )

            val result = orangeRepository.getAllAccount()

            assertEquals(expectedAccounts, result)
        }
    }

}


