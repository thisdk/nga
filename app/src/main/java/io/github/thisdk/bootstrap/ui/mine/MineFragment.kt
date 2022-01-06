package io.github.thisdk.bootstrap.ui.mine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.thisdk.bootstrap.architecture.viewbinding.BaseBindingFragment
import io.github.thisdk.bootstrap.databinding.FragmentMineBinding

@AndroidEntryPoint
class MineFragment : BaseBindingFragment<FragmentMineBinding>() {

    private val viewModel: MineViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.text.observe(viewLifecycleOwner, {
            binding.textMine.text = it
        })
    }

}