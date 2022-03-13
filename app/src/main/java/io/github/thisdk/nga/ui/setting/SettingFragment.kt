package io.github.thisdk.nga.ui.setting

import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.thisdk.nga.arch.viewbinding.BaseBindingFragment
import io.github.thisdk.nga.databinding.FragmentSettingBinding

@AndroidEntryPoint
class SettingFragment : BaseBindingFragment<FragmentSettingBinding>() {

    private val viewModel: SettingViewModel by activityViewModels()

    override fun initView() {
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textSetting.text = it
        }
    }

}