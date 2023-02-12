package com.orangeproject.orangebank.repository.mapper

import com.orangeproject.orangebank.business.models.OrangeTransaction
import com.orangeproject.orangebank.repository.models.transaction.Transaction
import com.orangeproject.orangebank.repository.models.transaction.TransactionResponse
import java.text.SimpleDateFormat
import java.util.*

object OrangeTransactionMapper {
    fun mapTransaction(response: TransactionResponse): List<OrangeTransaction> =
        response.Data.Transaction.map { mapTransaction(it) }

    private fun mapTransaction(transaction: Transaction?) = OrangeTransaction(

        transactionId = transaction?.transactionId,
        amount = transaction?.amount?.Amount + " €",
        creditDebitIndicator = transaction?.creditDebitIndicator,
        status = transaction?.status,
        dateTime = transaction?.valueDateTime?.let { formatDate(it).first } ,
        date = transaction?.valueDateTime?.let { formatDate(it).second },
        transactionInformation = transaction?.transactionInformation,
        transactionReference = transaction?.transactionReference

        )

    private fun formatDate(_date: String) : Pair<Date, String> {

        val originalFormat = "yyyy-MM-dd'T'HH:mm:ssZ"
        val targetFormat = "dd MMM yyyy HH:mm:ss"

        val originalDateFormat = SimpleDateFormat(originalFormat)
        val targetDateFormat = SimpleDateFormat(targetFormat)

        val date = originalDateFormat.parse(_date)
        val formattedDate = targetDateFormat.format(date)


        return Pair(date, formattedDate)
    }
}

