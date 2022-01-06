package io.github.thisdk.bootstrap.architecture.mvi

import androidx.lifecycle.ViewModel
import io.github.thisdk.bootstrap.architecture.component.SingleLiveEvents
import io.github.thisdk.bootstrap.architecture.ktx.asLiveData

abstract class MviViewModel<VA : MviViewAction, VE : MviViewEvent> : ViewModel() {

    protected val _viewEvents: SingleLiveEvents<VE> = SingleLiveEvents()
    val viewEvents = _viewEvents.asLiveData()

    abstract fun dispatch(action: VA)

}