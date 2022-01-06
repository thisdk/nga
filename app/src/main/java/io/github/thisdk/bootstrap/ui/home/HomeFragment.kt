package io.github.thisdk.bootstrap.ui.home

import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.thisdk.bootstrap.architecture.ktx.observeState
import io.github.thisdk.bootstrap.architecture.mvi.BaseMviFragment
import io.github.thisdk.bootstrap.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseMviFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by activityViewModels()

    override fun initViewModel() {

        viewModel.viewStates.let { state ->
            state.observeState(viewLifecycleOwner, HomeViewState::value1) {
                binding.textValue1.text = it
            }
            state.observeState(viewLifecycleOwner, HomeViewState::value2) {
                binding.textValue2.text = it
            }
        }


        binding.btnGetValue1.setOnClickListener {
            viewModel.dispatch(HomeViewAction.Value1)
        }
        binding.btnGetValue2.setOnClickListener {

        }
        binding.btnGetValue3.setOnClickListener {

        }
        binding.btnGetValue4.setOnClickListener {
            viewModel.dispatch(HomeViewAction.FetchData)
        }

    }

}