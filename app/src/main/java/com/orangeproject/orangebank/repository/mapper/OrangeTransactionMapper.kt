package com.orangeproject.orangebank.repository.mapper

import com.orangeproject.orangebank.business.models.OrangeTransaction
import com.orangeproject.orangebank.repository.models.transaction.Transaction
import com.orangeproject.orangebank.repository.models.transaction.TransactionResponse
import java.text.SimpleDateFormat
import java.util.*

object OrangeTransactionMapper {
    fun mapTransaction(response: TransactionResponse): List<OrangeTransaction>? =
        sortTransaction(response.Data.Transaction.map { mapTransaction(it) })
}

private fun mapTransaction(transaction: Transaction?) = OrangeTransaction(

    TransactionId = transaction?.TransactionId,
    Amount = transaction?.Amount?.Amount,
    CreditDebitIndicator = transaction?.CreditDebitIndicator,
    Status = transaction?.Status,
    dateTime = transaction?.ValueDateTime?.let { formatDate(it).first } ,
    date = transaction?.ValueDateTime?.let { formatDate(it).second },

)

private fun sortTransaction(transaction: List<OrangeTransaction>?): List<OrangeTransaction>?{


    var sortList = transaction?.toMutableList()

    sortList?.sortWith(Comparator { event1, event2 -> event1.date?.let { event2.date!!.compareTo(it) }!! })


    return sortList?.toList()
}


fun formatDate(_date: String) : Pair<Date, String> {

    val dateString = _date
    val originalFormat = "yyyy-MM-dd'T'HH:mm:ssZ"
    val targetFormat = "dd MMM yyyy HH:mm:ss"

    val originalDateFormat = SimpleDateFormat(originalFormat)
    val targetDateFormat = SimpleDateFormat(targetFormat)

    val date = originalDateFormat.parse(dateString)
    val formattedDate = targetDateFormat.format(date)


    return Pair(date, formattedDate)
}