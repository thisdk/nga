package io.github.thisdk.nga.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewScoped
import io.github.thisdk.nga.domain.Category
import io.github.thisdk.nga.ui.home.forum.ForumFragment
import javax.inject.Inject

class CategoryAdapter @Inject constructor(val fragment: Fragment) : FragmentStateAdapter(fragment) {

    var data: MutableList<Category> = mutableListOf()
        set(value) {
            field = value
            notifyItemRangeChanged(0, value.size)
        }

    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment {
        val fragment = ForumFragment()
        fragment.arguments = Bundle().apply {
            putString("cid", data[position].cid)
        }
        return fragment
    }

}