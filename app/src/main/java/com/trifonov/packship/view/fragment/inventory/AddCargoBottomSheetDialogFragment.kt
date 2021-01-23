package com.trifonov.packship.view.fragment.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.trifonov.packship.R
import com.trifonov.packship.databinding.FragmentAddCargoBinding
import com.trifonov.packship.util.setNavigationResult
import com.trifonov.packship.viewmodel.inventory.AddCargoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCargoBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private val viewModel: AddCargoViewModel by viewModels()

    private lateinit var binding: FragmentAddCargoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_cargo, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.back.observe(viewLifecycleOwner) {
            setNavigationResult("NEW_CARGO", null)
            findNavController().popBackStack()
        }
    }
}