package io.github.thisdk.bootstrap.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import io.github.thisdk.bootstrap.databinding.FragmentMineBinding

class MineFragment : Fragment() {

    private val viewModel: MineViewModel by activityViewModels()

    private var _binding: FragmentMineBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMineBinding.inflate(inflater, container, false)

        val root: View = binding.root

        viewModel.text.observe(viewLifecycleOwner, {
            binding.textMine.text = it
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}