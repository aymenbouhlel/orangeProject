package com.orangeproject.orangebank.ui.transaction

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.SimpleItemAnimator
import com.orangeproject.R
import com.orangeproject.databinding.FragmentTransactionBinding
import com.orangeproject.orangebank.business.models.OrangeTransaction
import com.orangeproject.orangebank.ui.detailsTransaction.DetailsTransactionFragment
import com.orangeproject.orangebank.ui.transaction.adapter.TransactionListAdapter
import com.orangeproject.utils.Constant

class TransactionFragment : Fragment() {

    private var _binding: FragmentTransactionBinding? = null
    private val binding get() = _binding!!
    private lateinit var listTransaction:  List<OrangeTransaction>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transactionListAdapter =
            TransactionListAdapter(requireContext()) {
                val bundle = Bundle().apply {
                    putSerializable(DetailsTransactionFragment.DETAILS_DATA, it as java.io.Serializable)
                }
                findNavController().navigate(
                    R.id.action_AccountFragment_to_TransactionDetailFragment, bundle
                    )
            }

        val args = arguments

        listTransaction= args?.getSerializable(Constant.ARGUMENT_KEY) as List<OrangeTransaction>


        binding.run {
            binding.recycleView.adapter = transactionListAdapter

            val itemAnimator = binding.recycleView.itemAnimator as SimpleItemAnimator
            itemAnimator.supportsChangeAnimations = false
            binding.recycleView.itemAnimator = itemAnimator

            transactionListAdapter.submitList(listTransaction)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}