package com.orangeproject.orangebank.business.models

import java.io.Serializable
import java.util.Date


data class OrangeTransaction(
    val TransactionId: String?,
    val Amount: String?,
    val CreditDebitIndicator: String?,
    val Status: String?,
    val dateTime: Date?,
    val date: String?

): Serializable
