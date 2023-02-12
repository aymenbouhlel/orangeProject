package com.orangeproject.orangebank.ui.detailsTransaction

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.orangeproject.databinding.FragmentDetailsTransactionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsTransactionFragment : Fragment() {

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

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        arguments?.getSerializable(DETAILS_DATA)?.let {


        } ?: run {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}