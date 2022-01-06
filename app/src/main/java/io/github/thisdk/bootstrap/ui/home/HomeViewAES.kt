package io.github.thisdk.bootstrap.ui.home

import androidx.annotation.StringRes

sealed class HomeViewAction {
    object FetchData : HomeViewAction()
    object Value1 : HomeViewAction()
}

sealed class HomeViewEvent {
    data class ShowToastStr(val message: String) : HomeViewEvent()
    data class ShowToastRes(@StringRes val message: Int) : HomeViewEvent()
}

data class HomeViewState(
    val value1: String = "value1 -> hello",
    val value2: String = "Kotlin"
)