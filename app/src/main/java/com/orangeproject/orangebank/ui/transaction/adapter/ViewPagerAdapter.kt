package com.orangeproject.orangebank.ui.transaction.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.orangeproject.orangebank.business.models.OrangeTransaction
import com.orangeproject.orangebank.ui.transaction.TransactionFragment
import com.orangeproject.utils.Constant

private const val NUM_TABS = 2

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    _listTransaction: Pair<List<OrangeTransaction>, List<OrangeTransaction>>
) :

    FragmentStateAdapter(fragmentManager, lifecycle) {

    val listTransaction: Pair<List<OrangeTransaction>, List<OrangeTransaction>> = _listTransaction
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {

        val fragmentDebit = TransactionFragment()
        val fragmentCredit = TransactionFragment()

        fragmentCredit.arguments = Bundle().apply {
            putSerializable(Constant.ARGUMENT_KEY, listTransaction.first as java.io.Serializable)
        }

        fragmentDebit.arguments = Bundle().apply {
            putSerializable(Constant.ARGUMENT_KEY, listTransaction.second as java.io.Serializable)
        }

        when (position) {
            0 -> return fragmentCredit
            1 -> return fragmentDebit
        }
        return TransactionFragment()
    }
}