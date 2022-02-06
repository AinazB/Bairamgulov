package com.ainaz.tinkofftest.ui.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.PagerSnapHelper
import com.ainaz.tinkofftest.App
import com.ainaz.tinkofftest.R
import com.ainaz.tinkofftest.databinding.FragmentTopBinding
import com.ainaz.tinkofftest.domain.usecase.GetTopGifsUseCase
import com.ainaz.tinkofftest.ui.adapter.GifLoadStateAdapter
import com.ainaz.tinkofftest.ui.latest.GifsAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopFragment : Fragment(R.layout.fragment_top) {

    private var _binding: FragmentTopBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainLoadStateHolder: GifLoadStateAdapter.Holder

    @Inject
    lateinit var getTopGifs: GetTopGifsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
    }

    private fun setupList() {
        val adapter = GifsAdapter()
        binding.topRv.adapter = adapter
        mainLoadStateHolder = GifLoadStateAdapter.Holder(
            binding.loadStateView
        ) { adapter.retry() }
        observeLoadState(adapter)
        PagerSnapHelper().attachToRecyclerView(binding.topRv)
        lifecycleScope.launchWhenStarted {
            getTopGifs().collectLatest {
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
