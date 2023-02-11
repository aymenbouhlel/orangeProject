package com.orangeproject.orangebank.business.models


data class OrangeTransaction(
    val TransactionId: String?,
    val Amount: String?,
    val CreditDebitIndicator: String?,
    val Status: String?,
    val ValueDateTime: String?

)
