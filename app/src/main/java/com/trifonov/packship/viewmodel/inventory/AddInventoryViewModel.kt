package com.trifonov.packship.viewmodel.inventory

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.trifonov.packship.repository.InventoryRepository
import com.trifonov.packship.util.DispatcherProvider

class AddInventoryViewModel @ViewModelInject constructor(
    private val inventoryRepository: InventoryRepository,
    private val dispatcherProvider: DispatcherProvider

) : ViewModel() {



    fun onSaveButtonClicked() {

    }
}