package com.trifonov.packship.viewmodel.supplier

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.trifonov.packship.network.PackShipResponse
import com.trifonov.packship.network.model.supplier.Supplier
import com.trifonov.packship.repository.SupplierRepository
import com.trifonov.packship.util.DispatcherProvider
import kotlinx.coroutines.launch
import timber.log.Timber

class SuppliersViewModel @ViewModelInject constructor(
    private val supplierRepository: SupplierRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel(), LifecycleObserver {

    val suppliers = MutableLiveData<List<Supplier>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getSuppliers() {
        viewModelScope.launch(dispatcherProvider.ui) {
            when (val response = supplierRepository.getSuppliers()) {
                is PackShipResponse.Success -> {
                    Timber.e(response.data.toString())
                    suppliers.value = response.data
                }
                is PackShipResponse.Error -> {
                    Timber.d(response.message)
                }
            }
        }
    }
}