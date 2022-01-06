package io.github.thisdk.bootstrap.ui.home

import io.github.thisdk.bootstrap.architecture.mvi.MviViewAction
import io.github.thisdk.bootstrap.architecture.mvi.MviViewEvent

sealed class HomeViewAction : MviViewAction {
    object FetchData : HomeViewAction()
    object Value1 : HomeViewAction()
}

sealed class HomeViewEvent : MviViewEvent() {
    object ShowDialog : HomeViewEvent()
}

data class HomeViewState(
    val value1: String = "value1 -> hello",
    val value2: String = "Kotlin"
)