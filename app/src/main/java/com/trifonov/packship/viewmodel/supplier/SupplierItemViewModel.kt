package com.trifonov.packship.viewmodel.supplier

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.trifonov.packship.R
import com.trifonov.packship.network.model.supplier.Supplier

class SupplierItemViewModel() : ViewModel() {

    private val supplier = MutableLiveData<Supplier>()

    val loadedContainers = supplier.map { it.loadedContainers.toString() }

    val loadedWeight = supplier.map { it.loadedWeight.toString() }

    val maximumWeight = supplier.map { it.maximumWeight.toString() }

    val supplierIcon = supplier.map {
        when(it.state) {
            "Available" -> R.drawable.ic_supplier_truck_colored
            else -> R.drawable.ic_supplier_truck_white
        }
    }

    val isAvailable = supplier.map {
        it.state != "Available"
    }

    fun bind(item: Supplier) {
        supplier.value = item
    }
}