package io.github.thisdk.nga.arch.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseBindingAdapter<E : Any, VB : ViewBinding> : RecyclerView.Adapter<BaseBindingViewHolder<VB>>() {

    open var data: MutableList<E> = mutableListOf()
        set(value) {
            field = value
            notifyItemRangeChanged(0, value.size)
        }

    override fun onBindViewHolder(holder: BaseBindingViewHolder<VB>, position: Int) {
        onBindViewHolder(holder.binding, position, data[position])
    }

    abstract fun onBindViewHolder(binding: VB, position: Int,  item: E)

    override fun getItemCount(): Int = data.size

}

