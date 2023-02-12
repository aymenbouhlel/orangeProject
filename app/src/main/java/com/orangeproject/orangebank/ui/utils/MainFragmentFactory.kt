package com.myapplicationtest.aymsoft.ui.listUsers.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.orangeproject.orangebank.ui.account.AccountFragment
import com.orangeproject.orangebank.ui.detailsTransaction.DetailsTransactionFragment
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment = when (className) {
      //  AccountFragment::class.java.name -> AccountFragment()
      //  DetailsTransactionFragment::class.java.name -> DetailsTransactionFragment()

        else -> super.instantiate(classLoader, className)
    }
}