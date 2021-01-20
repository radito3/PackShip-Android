package com.trifonov.packship.viewmodel.container

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.trifonov.packship.network.model.containers.Container

class ContainerItemViewModel() : ViewModel() {

    private val container = MutableLiveData<Container>()

    val cargos = container.map { it.cargos.toString() }

    val maximumWeight = container.map { it.maximumWeight.toString() }

    val state = container.map { it.state }

    fun bind(item: Container) {
        container.value = item
    }
}