package io.github.thisdk.nga.ui.home

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.github.thisdk.nga.architecture.ktx.observeEvent
import io.github.thisdk.nga.architecture.ktx.observeState
import io.github.thisdk.nga.architecture.ktx.toast
import io.github.thisdk.nga.architecture.mvi.BaseMviFragment
import io.github.thisdk.nga.databinding.FragmentHomeBinding
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseMviFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by activityViewModels()

    @Inject
    lateinit var categoryAdapter: CategoryAdapter

    override fun initViewModel() {

        binding.viewPager2.adapter = categoryAdapter

        TabLayoutMediator(binding.tabCategory, binding.viewPager2) { tab, position ->
            tab.text = categoryAdapter.data[position].name
        }.attach()

        viewModel.viewStates.let { state ->

            state.observeState(viewLifecycleOwner, HomeViewState::category) {
                categoryAdapter.data = it.toMutableList()
            }

            state.observeState(viewLifecycleOwner, HomeViewState::currentItem) {
                if (binding.viewPager2.currentItem != it) {
                    binding.viewPager2.setCurrentItem(it, false)
                }
            }

        }

        viewModel.viewEvents.observeEvent(viewLifecycleOwner) {
            when (it) {
                is HomeViewEvent.ShowToastStr -> context?.toast(it.message)
                is HomeViewEvent.ShowToastRes -> context?.toast(it.message)
            }
        }

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewModel.dispatch(HomeViewAction.PageChange(position))
            }
        })

        viewModel.dispatch(HomeViewAction.QueryCategory)

    }


}