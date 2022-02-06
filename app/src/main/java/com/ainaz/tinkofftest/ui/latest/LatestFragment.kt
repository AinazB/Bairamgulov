package com.ainaz.tinkofftest.ui.latest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.PagerSnapHelper
import com.ainaz.tinkofftest.App
import com.ainaz.tinkofftest.databinding.FragmentLatestBinding
import com.ainaz.tinkofftest.domain.usecase.GetLatestGifsUseCase
import com.ainaz.tinkofftest.ui.adapter.GifLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

class LatestFragment : Fragment() {
    private var _binding: FragmentLatestBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainLoadStateHolder: GifLoadStateAdapter.Holder

    @Inject
    lateinit var getLatestGifs: GetLatestGifsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLatestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
    }

    private fun setupList() {
        val adapter = GifsAdapter()
        binding.latestRv.adapter = adapter
        mainLoadStateHolder = GifLoadStateAdapter.Holder(
            binding.loadStateView
        ) { adapter.retry() }
        observeLoadState(adapter)
        PagerSnapHelper().attachToRecyclerView(binding.latestRv)
        lifecycleScope.launchWhenStarted {
            getLatestGifs().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun observeLoadState(adapter: GifsAdapter) = lifecycleScope.launch {
        adapter.loadStateFlow.debounce(200).collectLatest { state ->
            mainLoadStateHolder.bind(state.refresh)
        }
    }

}