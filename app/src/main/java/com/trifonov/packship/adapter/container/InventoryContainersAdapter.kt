package com.trifonov.packship.adapter.container

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.trifonov.packship.databinding.ItemInventoryContainerBinding
import com.trifonov.packship.network.model.containers.Container
import com.trifonov.packship.util.SingleLiveEvent
import com.trifonov.packship.viewmodel.inventory.InventoryContainerItemViewModel

class InventoryContainersAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val onContainerClicked: SingleLiveEvent<String>
) :
    RecyclerView.Adapter<InventoryContainersAdapter.InventoryContainerItemViewHolder>() {

    private var containers: List<Container> = listOf()

    fun setContainers(containers: List<Container>) {
        this.containers = containers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InventoryContainerItemViewHolder {
        val viewModel = InventoryContainerItemViewModel(onContainerClicked)

        val binding = ItemInventoryContainerBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return InventoryContainerItemViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: InventoryContainerItemViewHolder, position: Int) {
        holder.onBind(containers[position])
    }

    override fun getItemCount(): Int = containers.size

    inner class InventoryContainerItemViewHolder(
        private val binding: ItemInventoryContainerBinding,
        private val viewModel: InventoryContainerItemViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = viewModel
            binding.lifecycleOwner = lifecycleOwner
        }

        fun onBind(item: Container) {
            viewModel.bind(item)
            binding.executePendingBindings()
        }
    }
}