package com.trifonov.packship.view.fragment.inventory

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.trifonov.packship.R
import com.trifonov.packship.adapter.inventory.InventoriesAdapter
import com.trifonov.packship.databinding.FragmentInventoriesBinding
import com.trifonov.packship.util.getNavigationResultLiveData
import com.trifonov.packship.viewmodel.inventory.InventoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InventoriesFragment : Fragment() {

    private val viewModel: InventoriesViewModel by viewModels()

    private lateinit var binding: FragmentInventoriesBinding

    private lateinit var inventoriesAdapter: InventoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_inventories, container, false
        )

        lifecycle.addObserver(viewModel)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inventoriesAdapter = InventoriesAdapter(viewLifecycleOwner, viewModel.onInventoryClicked)

        binding.rcvInventories.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = inventoriesAdapter
        }

        viewModel.inventories.observe(viewLifecycleOwner) { inventories ->
            inventoriesAdapter.setInventories(inventories)
        }

        viewModel.addInventory.observe(viewLifecycleOwner) {

            findNavController()
                .navigate(
                    InventoriesFragmentDirections
                        .actionInventoriesFragmentToAddInventoryFragment()
                )
        }

        viewModel.onInventoryClicked.observe(viewLifecycleOwner) { id ->

            findNavController()
                .navigate(
                    InventoriesFragmentDirections
                        .actionInventoriesFragmentToInventoryContainersFragment(id)
                )
        }

        getNavigationResultLiveData<Unit>("NEW_INVENTORY")?.observe(viewLifecycleOwner, {
            viewModel.getInventories()
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_test, menu)
        menu.findItem(R.id.menu_preferences).icon.setTint(resources.getColor(R.color.white, null))
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_preferences -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}