package io.github.thisdk.bootstrap.architecture.mvi

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import io.github.thisdk.bootstrap.architecture.viewbinding.BaseBindingFragment

abstract class BaseMviFragment<VM : ViewModel, VB : ViewBinding> : BaseBindingFragment<VB>() {

    protected abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    protected abstract fun initViewModel()

}