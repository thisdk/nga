package io.github.thisdk.nga.architecture.viewbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import io.github.thisdk.nga.utils.ViewBindingUtil

abstract class BaseBindingActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewBindingUtil.inflateWithGeneric(this, layoutInflater)
        setContentView(binding.root)
    }

}