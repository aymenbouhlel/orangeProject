package com.orangeproject.orangebank.business.models


data class OrangeAccount(
    val accountId: String?,
    val nickname: String?,
    val status: String?,
    val transactionsUrl: String?
) {
    override fun toString(): String {
        return "$nickname : N°$accountId"
    }
}