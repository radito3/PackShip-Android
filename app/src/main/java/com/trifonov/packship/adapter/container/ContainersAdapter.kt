package com.trifonov.packship.adapter.container

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.trifonov.packship.databinding.ItemContainerBinding
import com.trifonov.packship.network.model.containers.Container
import com.trifonov.packship.viewmodel.container.ContainerItemViewModel

class ContainersAdapter(
    private val lifecycleOwner: LifecycleOwner
) :
    RecyclerView.Adapter<ContainersAdapter.ContainerItemViewHolder>() {

    private var containers: List<Container> = listOf()

    fun setContainers(containers: List<Container>) {
        this.containers = containers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContainerItemViewHolder {
        val viewModel = ContainerItemViewModel()

        val binding = ItemContainerBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ContainerItemViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: ContainerItemViewHolder, position: Int) {
        holder.onBind(containers[position])
    }

    override fun getItemCount(): Int = containers.size

    inner class ContainerItemViewHolder(
        private val binding: ItemContainerBinding,
        private val viewModel: ContainerItemViewModel
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