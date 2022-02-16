package io.github.thisdk.nga.ui.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.thisdk.nga.architecture.viewbinding.BaseBindingFragment
import io.github.thisdk.nga.databinding.FragmentSettingBinding

@AndroidEntryPoint
class SettingFragment : BaseBindingFragment<FragmentSettingBinding>() {

    private val viewModel: SettingViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textSetting.text = it
        }
    }

}