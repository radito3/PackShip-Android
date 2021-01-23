package com.trifonov.packship.view.fragment.shipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.trifonov.packship.R
import com.trifonov.packship.adapter.container.ContainersAdapter
import com.trifonov.packship.databinding.FragmentShipmentContainersBinding
import com.trifonov.packship.viewmodel.shipment.ShipmentContainersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShipmentContainersFragment : Fragment() {
    private val viewModel: ShipmentContainersViewModel by viewModels()

    private lateinit var binding: FragmentShipmentContainersBinding

    private lateinit var shipmentsAdapter: ContainersAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shipment_containers, container, false
        )

        lifecycle.addObserver(viewModel)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shipmentsAdapter = ContainersAdapter(viewLifecycleOwner, viewModel.onContainerClicked)


        binding.rcvContainers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = shipmentsAdapter
        }

        viewModel.containers.observe(viewLifecycleOwner) { shipments ->

            shipmentsAdapter.setContainers(shipments)
        }
    }
}