package io.github.thisdk.bootstrap.architecture.component

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvents<T> : MutableLiveData<List<T>>() {

    private val pending = AtomicBoolean(false)
    private val eventList = mutableListOf<List<T>>()

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in List<T>>) {
        super.observe(owner, { t ->
            if (pending.compareAndSet(true, false)) {
                eventList.clear()
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(t: List<T>?) {
        pending.set(true)
        t?.let {
            eventList.add(it)
        }
        val list = eventList.flatten()
        super.setValue(list)
    }

    fun setEvent(vararg values: T) {
        this.value = values.toList()
    }

}