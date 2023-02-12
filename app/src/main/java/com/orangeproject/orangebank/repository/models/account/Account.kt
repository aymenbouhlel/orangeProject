package com.orangeproject.orangebank.repository.models.account

import com.google.gson.annotations.SerializedName

data class Account(
    @field:SerializedName("Account")
    val account: List<AccountX>,

    @field:SerializedName("AccountId")
    val accountId: String,

    @field:SerializedName("AccountSubType")
    val accountSubType: String,

    @field:SerializedName("AccountType")
    val accountType: String,

    @field:SerializedName("Currency")
    val currency: String,

    @field:SerializedName("Nickname")
    val nickname: String,

    @field:SerializedName("OpeningDate")
    val openingDate: String,

    @field:SerializedName("Status")
    val status: String,

    @field:SerializedName("StatusUpdateDateTime")
    val statusUpdateDateTime: String,

    @field:SerializedName("transactionsUrl")
    val transactionsUrl: String
)