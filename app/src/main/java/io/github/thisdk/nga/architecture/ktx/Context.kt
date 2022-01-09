package io.github.thisdk.nga.architecture.ktx

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun Context.toast(@StringRes message: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}