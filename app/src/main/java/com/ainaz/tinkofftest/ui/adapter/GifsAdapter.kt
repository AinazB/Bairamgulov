package com.ainaz.tinkofftest.ui.latest

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ainaz.tinkofftest.databinding.GifListItemBinding
import com.ainaz.tinkofftest.domain.model.Gif
import com.bumptech.glide.Glide

class GifsAdapter : PagingDataAdapter<Gif, GifsAdapter.Holder>(GifsDiffCallback()) {

    class Holder(val binding: GifListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder.binding) {
            getItem(position)?.let { loadGif(gifIv, it.gifUrl) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GifListItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    private fun loadGif(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}

class GifsDiffCallback : DiffUtil.ItemCallback<Gif>() {
    override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem.id == newItem.id

    }

    override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem == newItem
    }

}