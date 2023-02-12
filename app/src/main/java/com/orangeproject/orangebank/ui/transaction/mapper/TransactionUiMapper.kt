package com.orangeproject.orangebank.ui.transaction.mapper

import com.orangeproject.orangebank.business.models.OrangeTransaction
import com.orangeproject.utils.Constant

object TransactionUiMapper {


 fun mapTransaction(listTransaction: List<OrangeTransaction>) : Pair<List<OrangeTransaction>, List<OrangeTransaction>>{


     val listCredit: MutableList<OrangeTransaction> = mutableListOf()
     val listDebit: MutableList<OrangeTransaction> = mutableListOf()


    for (i in listTransaction.indices) {

        if (listTransaction[i].creditDebitIndicator == Constant.credit) {
            listCredit.add(listTransaction[i])
        }
        if (listTransaction[i].creditDebitIndicator == Constant.debit) {
            listDebit.add(listTransaction[i])
        }
    }


    return Pair(listCredit.take(2), listDebit.take(2))
}
}



