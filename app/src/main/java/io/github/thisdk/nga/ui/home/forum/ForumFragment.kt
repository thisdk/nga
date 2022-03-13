package io.github.thisdk.nga.ui.home.forum

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.thisdk.nga.arch.ktx.observeEvent
import io.github.thisdk.nga.arch.ktx.observeState
import io.github.thisdk.nga.arch.ktx.toast
import io.github.thisdk.nga.arch.mvi.BaseMviFragment
import io.github.thisdk.nga.databinding.FragmentForumBinding
import javax.inject.Inject

@AndroidEntryPoint
class ForumFragment : BaseMviFragment<FragmentForumBinding, ForumViewModel>() {

    override val viewModel: ForumViewModel by viewModels()

    private val cid: String by lazy { arguments?.getString("cid")!! }

    @Inject
    lateinit var adapter: ForumAdapter

    override fun initViewModel() {

        binding.rvForumRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.rvForumRecyclerView.adapter = adapter

        viewModel.viewStates.let { state ->

            state.observeState(viewLifecycleOwner, ForumViewState::forum) {
                adapter.data = it.toMutableList()
            }
        }

        viewModel.viewEvents.observeEvent(viewLifecycleOwner) {
            when (it) {
                is ForumViewEvent.ShowToastStr -> context?.toast(it.message)
                is ForumViewEvent.ShowToastRes -> context?.toast(it.message)
            }
        }

        viewModel.dispatch(ForumViewAction.QueryForum(cid))

    }

}