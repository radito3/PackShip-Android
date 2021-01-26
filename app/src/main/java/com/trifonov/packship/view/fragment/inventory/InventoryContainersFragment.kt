package com.trifonov.packship.view.fragment.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.trifonov.packship.R
import com.trifonov.packship.adapter.container.InventoryContainersAdapter
import com.trifonov.packship.databinding.FragmentInventoryContainersBinding
import com.trifonov.packship.viewmodel.inventory.InventoryContainersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InventoryContainersFragment : Fragment() {

    private val viewModel: InventoryContainersViewModel by viewModels()

    private lateinit var binding: FragmentInventoryContainersBinding
    private lateinit var containersAdapter: InventoryContainersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_inventory_containers, container, false)

        lifecycle.addObserver(viewModel)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        containersAdapter =
            InventoryContainersAdapter(viewLifecycleOwner, viewModel.containerCargoes)

        binding.rcvContainers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = containersAdapter
        }

        viewModel.containers.observe(viewLifecycleOwner) { suppliers ->
            containersAdapter.setContainers(suppliers)
        }

        viewModel.containerCargoes.observe(viewLifecycleOwner) { containerId ->
            viewModel.id.value?.let { inventoryId ->
                findNavController()
                    .navigate(InventoryContainersFragmentDirections
                        .actionInventoryContainersFragmentToInventoryContainerCargoesFragment(
                            inventoryId, containerId))
            }
        }
    }
}