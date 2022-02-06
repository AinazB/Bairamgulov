package com.ainaz.tinkofftest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ainaz.tinkofftest.databinding.LoadStateBinding

typealias TryAgainAction = () -> Unit

class GifLoadStateAdapter(
    private val tryAgainAction: TryAgainAction
) : LoadStateAdapter<GifLoadStateAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LoadStateBinding.inflate(inflater, parent, false)
        return Holder(binding, tryAgainAction)
    }

    class Holder(
        private val binding: LoadStateBinding,
        private val tryAgainAction: TryAgainAction
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retry.setOnClickListener { tryAgainAction() }
        }

        fun bind(loadState: LoadState) = with(binding) {
            retry.visibility = if (loadState is LoadState.Error) View.VISIBLE else View.GONE
        }
    }
}