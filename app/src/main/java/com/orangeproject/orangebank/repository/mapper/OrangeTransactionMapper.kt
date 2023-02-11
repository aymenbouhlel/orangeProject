package com.orangeproject.orangebank.repository.mapper

import com.orangeproject.orangebank.business.models.OrangeTransaction
import com.orangeproject.orangebank.repository.models.transaction.Transaction
import com.orangeproject.orangebank.repository.models.transaction.TransactionResponse

object OrangeTransactionMapper {
    fun mapTransaction(response: TransactionResponse): List<OrangeTransaction> =
        response.Data.Transaction.map {
            mapTransaction(it)
        }
}

private fun mapTransaction(transaction: Transaction?) = OrangeTransaction(

    TransactionId = transaction?.TransactionId,
    Amount = transaction?.Amount?.Amount,
    CreditDebitIndicator = transaction?.CreditDebitIndicator,
    Status = transaction?.Status,
    ValueDateTime = transaction?.ValueDateTime
)
