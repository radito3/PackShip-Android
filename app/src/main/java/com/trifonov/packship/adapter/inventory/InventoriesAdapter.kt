package com.trifonov.packship.adapter.inventory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.trifonov.packship.databinding.ItemInventoryBinding
import com.trifonov.packship.network.model.inventory.Inventory
import com.trifonov.packship.util.SingleLiveEvent
import com.trifonov.packship.viewmodel.inventory.InventoryItemViewModel

class InventoriesAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val onInventoryClicked: SingleLiveEvent<String>
) : RecyclerView.Adapter<InventoriesAdapter.InventoryItemViewHolder>() {

    private var inventories: List<Inventory> = listOf()

    fun setInventories(inventories: List<Inventory>) {
        this.inventories = inventories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryItemViewHolder {
        val viewModel = InventoryItemViewModel(onInventoryClicked)
        val binding = ItemInventoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return InventoryItemViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: InventoryItemViewHolder, position: Int) {
        holder.onBind(inventories[position])
    }

    override fun getItemCount(): Int = inventories.size

    inner class InventoryItemViewHolder(
        private val binding: ItemInventoryBinding,
        private val viewModel: InventoryItemViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = viewModel
            binding.lifecycleOwner = lifecycleOwner
        }

        fun onBind(item: Inventory) {
            viewModel.bind(item)
            binding.executePendingBindings()
        }
    }
}