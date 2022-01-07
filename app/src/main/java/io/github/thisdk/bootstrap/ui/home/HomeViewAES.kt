package io.github.thisdk.bootstrap.ui.home

import androidx.annotation.StringRes

sealed class HomeViewAction {
    object Value1 : HomeViewAction()
    object Value2 : HomeViewAction()
    object Value3 : HomeViewAction()
    object LoadImage : HomeViewAction()
}

sealed class HomeViewEvent {
    data class ShowToastStr(val message: String) : HomeViewEvent()
    data class ShowToastRes(@StringRes val message: Int) : HomeViewEvent()
}

data class HomeViewState(
    val value1: String = "value1 -> hello",
    val value2: String = "Kotlin",
    val value3: String = "Java JVM",
    val url: String = ""
)