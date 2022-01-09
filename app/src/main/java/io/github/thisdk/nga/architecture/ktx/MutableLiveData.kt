package io.github.thisdk.nga.architecture.ktx

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> {
    return this
}

fun <T> MutableLiveData<T>.setState(reducer: T.() -> T) {
    this.value = this.value?.reducer()
}