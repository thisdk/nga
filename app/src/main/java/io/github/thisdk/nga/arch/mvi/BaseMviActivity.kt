package io.github.thisdk.nga.arch.mvi

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import io.github.thisdk.nga.arch.viewbinding.BaseBindingActivity

abstract class BaseMviActivity<VB : ViewBinding, VM : ViewModel> : BaseBindingActivity<VB>() {

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    protected abstract fun initViewModel()

}