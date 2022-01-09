package io.github.thisdk.nga.architecture.mvi

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import io.github.thisdk.nga.architecture.viewbinding.BaseBindingActivity

abstract class BaseMviActivity<VM : ViewModel, VB : ViewBinding> : BaseBindingActivity<VB>() {

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    protected abstract fun initViewModel()

}