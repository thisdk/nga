package io.github.thisdk.nga.architecture.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BaseBindingViewHolder<V : ViewBinding>(val binding: V) : RecyclerView.ViewHolder(binding.root)