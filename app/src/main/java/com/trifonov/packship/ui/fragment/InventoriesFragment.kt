package com.trifonov.packship.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.trifonov.packship.viewmodel.InventoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InventoriesFragment : Fragment() {

    private val viewModel: InventoriesViewModel by viewModels()
}