package com.orangeproject.orangebank.ui.transaction.mapper

import com.orangeproject.orangebank.business.models.OrangeTransaction
import com.orangeproject.orangebank.ui.transaction.models.TransactionUi

object TransactionUiMapper {


private fun mapTransaction(transaction: List<OrangeTransaction>) : Pair<List<TransactionUi>, List<TransactionUi>>{

    lateinit var result :  Pair<List<TransactionUi>, List<TransactionUi>>




    return result
}
}




//object OrangeTransactionMapper {
//    fun mapTransaction(response: TransactionResponse): List<OrangeTransaction> =
//        response.Data.Transaction.map {
//            mapTransaction(it)
//        }
//}
//
//private fun mapTransaction(transaction: Transaction?) = OrangeTransaction(
//
//    TransactionId = transaction?.TransactionId,
//    Amount = transaction?.Amount?.Amount,
//    CreditDebitIndicator = transaction?.CreditDebitIndicator,
//    Status = transaction?.Status,
//    ValueDateTime = transaction?.ValueDateTime
//)