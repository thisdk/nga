package io.github.thisdk.bootstrap.architecture.mvi

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding
import io.github.thisdk.bootstrap.architecture.ktx.observeEvent
import io.github.thisdk.bootstrap.architecture.viewbinding.BaseBindingFragment

abstract class BaseMviFragment<VM : MviViewModel<*>, VB : ViewBinding> : BaseBindingFragment<VB>() {

    protected abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBaseViewModel()
        initViewModel()
    }

    private fun initBaseViewModel() {
        viewModel.viewEvents.observeEvent(viewLifecycleOwner, {
            when (it) {
                is MviViewEvent.ShowToastStr -> showToast(it.message)
                is MviViewEvent.ShowToastRes -> showToast(it.message)
            }
        })
    }

    protected abstract fun initViewModel()

    protected fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(@StringRes msg: Int) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}