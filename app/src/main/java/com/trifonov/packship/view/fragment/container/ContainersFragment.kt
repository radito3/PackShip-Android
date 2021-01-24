package com.trifonov.packship.view.fragment.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.trifonov.packship.R
import com.trifonov.packship.adapter.container.ContainersAdapter
import com.trifonov.packship.databinding.FragmentContainersBinding
import com.trifonov.packship.viewmodel.container.ContainersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContainersFragment : Fragment() {

    private val viewModel: ContainersViewModel by viewModels()

    private lateinit var binding: FragmentContainersBinding

    private lateinit var containersAdapter: ContainersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_containers, container, false)

        lifecycle.addObserver(viewModel)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        containersAdapter = ContainersAdapter(viewLifecycleOwner, viewModel.onContainerClicked)

        binding.rcvContainers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = containersAdapter
        }

        viewModel.containers.observe(viewLifecycleOwner) { suppliers ->
            containersAdapter.setContainers(suppliers)
        }
    }
}