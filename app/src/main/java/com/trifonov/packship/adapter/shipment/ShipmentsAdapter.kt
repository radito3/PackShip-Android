package com.trifonov.packship.adapter.shipment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.trifonov.packship.databinding.ItemShipmentBinding
import com.trifonov.packship.network.model.shipment.Shipment
import com.trifonov.packship.util.SingleLiveEvent
import com.trifonov.packship.viewmodel.shipment.ShipmentItemViewModel

class ShipmentsAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val onShipmentClicked: SingleLiveEvent<String>
) :
    RecyclerView.Adapter<ShipmentsAdapter.ShipmentItemViewHolder>() {

    private var shipments: List<Shipment> = listOf()

    fun setShipments(shipments: List<Shipment>) {
        this.shipments = shipments
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipmentItemViewHolder {
        val viewModel = ShipmentItemViewModel(onShipmentClicked)

        val binding = ItemShipmentBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ShipmentItemViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: ShipmentItemViewHolder, position: Int) {
        holder.onBind(shipments[position])
    }

    override fun getItemCount(): Int = shipments.size

    inner class ShipmentItemViewHolder(
        private val binding: ItemShipmentBinding,
        private val viewModel: ShipmentItemViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = viewModel
            binding.lifecycleOwner = lifecycleOwner
        }

        fun onBind(item: Shipment) {
            viewModel.bind(item)
            binding.executePendingBindings()
        }
    }
}