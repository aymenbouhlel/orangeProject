package com.orangeproject.orangebank.repository.models.transaction

data class Transaction(
    val Amount: Amount,
    val BankTransactionCode: BankTransactionCode,
    val CreditDebitIndicator: String,
    val ProprietaryBankTransactionCode: ProprietaryBankTransactionCode,
    val Status: String,
    val TransactionId: String,
    val TransactionInformation: String,
    val TransactionReference: String,
    val ValueDateTime: String
)