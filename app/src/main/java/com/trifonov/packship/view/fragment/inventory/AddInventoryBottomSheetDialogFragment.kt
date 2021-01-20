package com.trifonov.packship.view.fragment.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.trifonov.packship.R
import com.trifonov.packship.databinding.FragmentAddInventoryBinding
import com.trifonov.packship.util.setNavigationResult
import com.trifonov.packship.viewmodel.inventory.AddInventoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddInventoryBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private val viewModel: AddInventoryViewModel by viewModels()

    private lateinit var binding: FragmentAddInventoryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_inventory, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setTitle(R.string.add_inventory)
        binding.toolbar.inflateMenu(R.menu.menu_bottom_sheet_dialog_fragment)

        binding.toolbar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener,
            androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                return when (item?.itemId) {
                    R.id.menu_save -> {
                        viewModel.onSaveButtonClicked()
                        true
                    }
                    else -> false
                }
            }
        })

        viewModel.back.observe(viewLifecycleOwner) {
            setNavigationResult("NEW_INVENTORY", null)
            findNavController().popBackStack()
        }
    }
}