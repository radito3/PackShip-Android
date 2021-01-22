package com.trifonov.packship.adapter.cargo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.trifonov.packship.databinding.ItemBoxedCargoBinding
import com.trifonov.packship.network.model.cargo.Cargo
import com.trifonov.packship.viewmodel.inventory.InventoryContainerCargoItemViewModel

class CargoesAdapter(
    private val lifecycleOwner: LifecycleOwner,
    ) :
    RecyclerView.Adapter<CargoesAdapter.CargoesItemViewHolder>() {

        private var cargoes: List<Cargo> = listOf()

        fun setCargoes(cargoes: List<Cargo>) {
            this.cargoes = cargoes
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CargoesItemViewHolder {
            val viewModel = InventoryContainerCargoItemViewModel()

            val binding = ItemBoxedCargoBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

            return CargoesItemViewHolder(binding, viewModel)
        }

        override fun onBindViewHolder(holder: CargoesItemViewHolder, position: Int) {
            holder.onBind(cargoes[position])
        }

        override fun getItemCount(): Int = cargoes.size

        inner class CargoesItemViewHolder(
            private val binding: ItemBoxedCargoBinding,
            private val viewModel: InventoryContainerCargoItemViewModel
        ) : RecyclerView.ViewHolder(binding.root) {

            init {
                binding.viewModel = viewModel
                binding.lifecycleOwner = lifecycleOwner
            }

            fun onBind(item: Cargo) {
                viewModel.bind(item)
                binding.executePendingBindings()
            }
        }
    }