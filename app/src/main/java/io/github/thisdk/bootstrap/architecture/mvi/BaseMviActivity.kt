package io.github.thisdk.bootstrap.architecture.mvi

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding
import io.github.thisdk.bootstrap.architecture.viewbinding.BaseBindingActivity

abstract class BaseMviActivity<VM : MviViewModel<*,*>, VB : ViewBinding> :
    BaseBindingActivity<VB>() {

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBaseViewModel()
        initViewModel()
    }

    private fun initBaseViewModel() {
        viewModel.viewEvents.observeEvent(this, {
            when (it) {
                is MviViewEvent.ShowToastStr -> showToast(it.message)
                is MviViewEvent.ShowToastRes -> showToast(it.message)
            }
        })
    }

    protected abstract fun initViewModel()

    protected fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(@StringRes msg: Int) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}