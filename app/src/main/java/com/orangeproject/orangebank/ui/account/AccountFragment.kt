package com.orangeproject.orangebank.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.orangeproject.R
import com.orangeproject.databinding.FragmentAccountBinding
import com.orangeproject.orangebank.business.models.OrangeAccount
import com.orangeproject.orangebank.business.models.OrangeTransaction
import com.orangeproject.orangebank.ui.transaction.adapter.ViewPagerAdapter
import com.orangeproject.utils.Constant
import com.orangeproject.utils.UiState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AccountFragment : Fragment() {
    private val accountViewModel: AccountViewModel by activityViewModels()
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private lateinit var listAccount: List<OrangeAccount>
    private lateinit var pager: ViewPager2
    private lateinit var tab: TabLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        _binding?.apply {
            viewModel = accountViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()

        _binding?.swipeRefreshLayout?.setOnRefreshListener {
            _binding?.swipeRefreshLayout?.isRefreshing = true

            accountViewModel.getAllAccount()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViewModel() {
        accountViewModel.listAccount.observe(viewLifecycleOwner) { account ->
            initSpinner(account)
            listAccount = account
            _binding?.swipeRefreshLayout?.isRefreshing = false
        }

        accountViewModel.updateUiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                UiState.ERROR -> {
                    _binding?.swipeRefreshLayout?.isRefreshing = false
                    Snackbar.make(
                        binding.contantView,
                        resources.getString(R.string.error_data),
                        Snackbar.LENGTH_LONG
                    ).show()

                    initPagerView(emptyList())
                }
                UiState.HIDE_LOADING -> {
                    _binding?.swipeRefreshLayout?.isRefreshing = false
                }
                else -> {
                    _binding?.swipeRefreshLayout?.isRefreshing = true
                }
            }
        }

        accountViewModel.listTransaction.observe(viewLifecycleOwner) { transaction ->
            initPagerView(transaction)
        }
    }

    private fun initSpinner(listAccount: List<OrangeAccount>) {

        var spinerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listAccount)
        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        _binding?.spinner?.adapter = spinerAdapter
        _binding?.spinner?.prompt = Constant.selectAccountMessage
        _binding?.spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                _binding?.swipeRefreshLayout?.isRefreshing = false

                listAccount[position].transactionsUrl?.let { accountViewModel.getTransaction(it) }

                _binding?.swipeRefreshLayout?.isRefreshing = false
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun initPagerView(list: List<OrangeTransaction>) {
        pager = _binding?.pager!!
        tab = _binding?.tab!!

        val titleArray = arrayOf( getString(R.string.credit),getString( R.string.debit))
        val adapter =
            activity?.supportFragmentManager?.let { ViewPagerAdapter(it, lifecycle, list) }
        pager.adapter = adapter
        TabLayoutMediator(tab, pager) { tab, position ->
            tab.text = titleArray[position]
        }.attach()
    }
}