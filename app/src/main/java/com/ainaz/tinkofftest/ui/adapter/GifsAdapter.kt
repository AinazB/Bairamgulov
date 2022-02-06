package com.ainaz.tinkofftest.ui.latest

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ainaz.tinkofftest.R
import com.ainaz.tinkofftest.databinding.GifListItemBinding
import com.ainaz.tinkofftest.domain.model.Gif
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.android.material.progressindicator.CircularProgressIndicator

class GifsAdapter : PagingDataAdapter<Gif, GifsAdapter.Holder>(GifsDiffCallback()) {

    class Holder(val binding: GifListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder.binding) {
            getItem(position)?.let {
                loadGif(gifIv, it.gifUrl)
                description.text = it.description
            }
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
            .placeholder(setupShimmer())
            .into(imageView)
    }

    private fun setupShimmer(): ShimmerDrawable {
        val shimmer =
            Shimmer.AlphaHighlightBuilder()
                .setDuration(1800)
                .setBaseAlpha(0.7f)
                .setHighlightAlpha(0.6f)
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()

        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }

        return shimmerDrawable
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