package io.github.thisdk.nga.architecture.viewbinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import io.github.thisdk.nga.utils.ViewBindingUtil

abstract class BaseBindingFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null

    protected val binding: VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewBindingUtil.inflateWithGeneric(this, layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}