package io.github.thisdk.bootstrap.ui.home

import androidx.annotation.StringRes

sealed class HomeViewAction {
    data class FetchThreadData(val fid: Int, val page: Int) : HomeViewAction()
}

sealed class HomeViewEvent {
    data class ShowToastStr(val message: String) : HomeViewEvent()
    data class ShowToastRes(@StringRes val message: Int) : HomeViewEvent()
}

data class HomeViewState(
    val url: String = "Hello"
)