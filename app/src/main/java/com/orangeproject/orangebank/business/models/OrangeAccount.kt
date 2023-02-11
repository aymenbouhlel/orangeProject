package com.orangeproject.orangebank.business.models


data class OrangeAccount(
    val AccountId: String?,
    val Nickname: String?,
    val Status: String?,
    val transactionsUrl: String?
) {
    override fun toString(): String {
        return "$Nickname : N°$AccountId"
    }
}