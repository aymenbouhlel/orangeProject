package com.orangeproject.orangebank.business.models

import java.io.Serializable
import java.util.Date


data class OrangeTransaction(
    val transactionId: String?,
    val amount: String?,
    val creditDebitIndicator: String?,
    val status: String?,
    val dateTime: Date?,
    val date: String?,
    val transactionInformation: String?,
    val transactionReference: String?,


): Serializable
