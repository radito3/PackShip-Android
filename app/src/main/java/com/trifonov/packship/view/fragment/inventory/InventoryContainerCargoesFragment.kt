package com.trifonov.packship.view.fragment.inventory

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
import com.trifonov.packship.adapter.cargo.CargoesAdapter
import com.trifonov.packship.databinding.FragmentBoxedCargoesBinding
import com.trifonov.packship.util.getNavigationResultLiveData
import com.trifonov.packship.viewmodel.inventory.InventoryContainerCargoesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InventoryContainerCargoesFragment : Fragment() {

    private val viewModel: InventoryContainerCargoesViewModel by viewModels()

    private lateinit var binding: FragmentBoxedCargoesBinding

    private lateinit var cargoesAdapter: CargoesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_boxed_cargoes, container, false
        )

        lifecycle.addObserver(viewModel)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cargoesAdapter = CargoesAdapter(viewLifecycleOwner)

        binding.rcvCargoes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cargoesAdapter
        }

        viewModel.cargoes.observe(viewLifecycleOwner) { cargoes ->
            cargoesAdapter.setCargoes(cargoes)
        }

        viewModel.addCargo.observe(viewLifecycleOwner) { (inventoryId, containerId) ->

            findNavController()
                .navigate(
                    InventoryContainerCargoesFragmentDirections
                        .actionInventoryContainerCargoesFragmentToAddCargoBottomSheetDialogFragment(
                            inventoryId,
                            containerId
                        )
                )
        }

        getNavigationResultLiveData<Unit>("NEW_CARGO")?.observe(viewLifecycleOwner, {
            viewModel.getCargoes()
        })
    }
}