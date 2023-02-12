package com.orangeproject.orangebank.ui.transaction.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.orangeproject.orangebank.business.models.OrangeTransaction
import com.orangeproject.orangebank.ui.transaction.TransactionFragment

private const val NUM_TABS = 2

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    _listTransaction: List<OrangeTransaction>
) :

    FragmentStateAdapter(fragmentManager, lifecycle) {

    val listTransaction: List<OrangeTransaction> = _listTransaction
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {

        val fragmentDebit = TransactionFragment()
        val fragmentCredit = TransactionFragment()
        var listDebitTransaction: MutableList<OrangeTransaction> = mutableListOf()
        var listCreditTransaction: MutableList<OrangeTransaction> = mutableListOf()


        for (i in listTransaction.indices) {

            if (listTransaction[i].CreditDebitIndicator == "Credit") {
                listCreditTransaction.add(listTransaction[i])
            }
            if (listTransaction[i].CreditDebitIndicator == "Debit") {
                listDebitTransaction.add(listTransaction[i])
            }
        }

        fragmentDebit.arguments = Bundle().apply {
            putSerializable("data_list", listDebitTransaction.take(2) as java.io.Serializable)
        }

        fragmentCredit.arguments = Bundle().apply {
            putSerializable("data_list", listCreditTransaction.take(2) as java.io.Serializable)
        }

        when (position) {
            0 -> return fragmentCredit
            1 -> return fragmentDebit
        }
        return TransactionFragment()
    }
}