package io.github.thisdk.nga.arch.mvi

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import io.github.thisdk.nga.arch.viewbinding.BaseBindingFragment

abstract class BaseMviFragment<VB : ViewBinding, VM : ViewModel> : BaseBindingFragment<VB>() {

    protected abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    protected abstract fun initViewModel()


}