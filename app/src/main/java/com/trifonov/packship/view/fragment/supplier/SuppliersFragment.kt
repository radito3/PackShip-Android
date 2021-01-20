package com.trifonov.packship.view.fragment.supplier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.trifonov.packship.R
import com.trifonov.packship.adapter.supplier.SuppliersAdapter
import com.trifonov.packship.databinding.FragmentSuppliersBinding
import com.trifonov.packship.viewmodel.supplier.SuppliersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuppliersFragment : Fragment() {

    private val viewModel: SuppliersViewModel by viewModels()

    private lateinit var binding: FragmentSuppliersBinding

    private lateinit var supplierAdapter: SuppliersAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_suppliers, container, false
        )

        lifecycle.addObserver(viewModel)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        supplierAdapter = SuppliersAdapter(viewLifecycleOwner)

        binding.rcvSuppliers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = supplierAdapter
        }

        viewModel.suppliers.observe(viewLifecycleOwner) { suppliers ->

            supplierAdapter.setSuppliers(suppliers)
        }

    }
}