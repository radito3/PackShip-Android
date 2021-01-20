package com.trifonov.packship.adapter.supplier

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.trifonov.packship.databinding.ItemSupplierBinding
import com.trifonov.packship.network.model.supplier.Supplier
import com.trifonov.packship.viewmodel.supplier.SupplierItemViewModel

class SuppliersAdapter(
    private val lifecycleOwner: LifecycleOwner
) :
    RecyclerView.Adapter<SuppliersAdapter.SupplierItemViewHolder>() {

    private var suppliers: List<Supplier> = listOf()

    fun setSuppliers(suppliers: List<Supplier>) {
        this.suppliers = suppliers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierItemViewHolder {
        val viewModel = SupplierItemViewModel()

        val binding = ItemSupplierBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return SupplierItemViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: SupplierItemViewHolder, position: Int) {
        holder.onBind(suppliers[position])
    }

    override fun getItemCount(): Int = suppliers.size

    inner class SupplierItemViewHolder(
        private val binding: ItemSupplierBinding,
        private val viewModel: SupplierItemViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = viewModel
            binding.lifecycleOwner = lifecycleOwner
        }

        fun onBind(item: Supplier) {
            viewModel.bind(item)
            binding.executePendingBindings()
        }
    }
}