package io.github.thisdk.nga.ui.home.forum

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewScoped
import io.github.thisdk.nga.R
import io.github.thisdk.nga.architecture.adapter.BaseBindingAdapter
import io.github.thisdk.nga.architecture.adapter.BaseBindingViewHolder
import io.github.thisdk.nga.databinding.ItemForumLayoutBinding
import io.github.thisdk.nga.domain.Forum
import javax.inject.Inject

class ForumAdapter @Inject constructor() : BaseBindingAdapter<Forum, ItemForumLayoutBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseBindingViewHolder<ItemForumLayoutBinding> {
        val binding =
            ItemForumLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForumViewHolder(binding)
    }

    override fun onBindViewHolder(binding: ItemForumLayoutBinding, position: Int, item: Forum) {
        binding.tvForumName.text = item.name
        binding.ivForumIcon.load("https://img4.nga.178.com/ngabbs/nga_classic/f/app/${item.fid}.png") {
            error(R.drawable.default_forum_icon)
        }
    }


}