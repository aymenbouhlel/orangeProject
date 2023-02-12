package com.orangeproject.orangebank.ui.detailsTransaction

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.orangeproject.databinding.FragmentDetailsTransactionBinding
import com.orangeproject.orangebank.business.models.OrangeTransaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsTransactionFragment : Fragment() {
    private val detailsTransactionViewModel: DetailsTransactionViewModel by activityViewModels()
    private var _binding: FragmentDetailsTransactionBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val DETAILS_DATA = "DETAILS_DATA"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsTransactionBinding.inflate(inflater, container, false)
        _binding?.apply {
            viewModel = detailsTransactionViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        arguments?.getSerializable(DETAILS_DATA)?.let {

            binding.item = it as OrangeTransaction
        } ?: run {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}