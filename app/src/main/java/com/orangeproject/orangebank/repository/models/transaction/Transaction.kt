package com.orangeproject.orangebank.repository.models.transaction

import com.google.gson.annotations.SerializedName
import java.util.*

data class Transaction(

    @field:SerializedName("Amount")
    val amount: Amount,

    @field:SerializedName("CreditDebitIndicator")
    val creditDebitIndicator: String,

    @field:SerializedName("Status")
    val status: String,

    @field:SerializedName("TransactionId")
    val transactionId: String,

    @field:SerializedName("TransactionInformation")
    val transactionInformation: String,

    @field:SerializedName("TransactionReference")
    val transactionReference: String,

    @field:SerializedName("ValueDateTime")
    val valueDateTime: String
)