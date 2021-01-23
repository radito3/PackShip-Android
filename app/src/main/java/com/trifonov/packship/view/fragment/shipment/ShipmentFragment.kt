package com.trifonov.packship.view.fragment.shipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.trifonov.packship.R
import com.trifonov.packship.adapter.shipment.ShipmentsAdapter
import com.trifonov.packship.databinding.FragmentShipmentsBinding
import com.trifonov.packship.viewmodel.shipment.ShipmentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShipmentFragment : Fragment() {

    private val viewModel: ShipmentsViewModel by viewModels()

    private lateinit var binding: FragmentShipmentsBinding

    private lateinit var shipmentsAdapter: ShipmentsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shipments, container, false
        )

        lifecycle.addObserver(viewModel)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shipmentsAdapter = ShipmentsAdapter(viewLifecycleOwner, viewModel.shipmentContainers)

        binding.rcvShipments.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = shipmentsAdapter
        }

        viewModel.shipments.observe(viewLifecycleOwner) { shipments ->

            shipmentsAdapter.setShipments(shipments)
        }

        viewModel.shipmentContainers.observe(viewLifecycleOwner) {

            findNavController()
                .navigate(
                    ShipmentFragmentDirections
                        .actionShipmentFragmentToShipmentContainersFragment(it)
                )
        }
    }
}