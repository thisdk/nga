package io.github.thisdk.nga.ui.mine

import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.thisdk.nga.arch.viewbinding.BaseBindingFragment
import io.github.thisdk.nga.databinding.FragmentMineBinding

@AndroidEntryPoint
class MineFragment : BaseBindingFragment<FragmentMineBinding>() {

    private val viewModel: MineViewModel by activityViewModels()

    override fun initView() {
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textMine.text = it
        }
    }

}