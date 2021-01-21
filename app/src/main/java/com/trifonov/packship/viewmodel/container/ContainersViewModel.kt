package com.trifonov.packship.viewmodel.container

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.trifonov.packship.network.PackShipResponse
import com.trifonov.packship.network.model.containers.Container
import com.trifonov.packship.repository.ContainerRepository
import com.trifonov.packship.util.DispatcherProvider
import com.trifonov.packship.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class ContainersViewModel @ViewModelInject constructor(
    private val containerRepository: ContainerRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel(), LifecycleObserver {

    val onContainerClicked = SingleLiveEvent<String>()
    val containers = MutableLiveData<List<Container>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getContainers() {
        viewModelScope.launch(dispatcherProvider.ui) {
            when (val response = containerRepository.getContainers()) {
                is PackShipResponse.Success -> {
                    Timber.e(response.data.toString())
                    containers.value = response.data
                }
                is PackShipResponse.Error -> {
                    Timber.d(response.message)
                }
            }
        }
    }
}