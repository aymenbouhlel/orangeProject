package com.orangeproject.orangebank.repository.models.transaction

import com.google.gson.annotations.SerializedName

data class Transaction(

    @field:SerializedName("Amount")
    val amount: Amount,

    @field:SerializedName("BankTransactionCode")
    val bankTransactionCode: BankTransactionCode,

    @field:SerializedName("CreditDebitIndicator")
    val creditDebitIndicator: String,

    @field:SerializedName("ProprietaryBankTransactionCode")
    val proprietaryBankTransactionCode: ProprietaryBankTransactionCode,

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