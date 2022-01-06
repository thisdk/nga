package io.github.thisdk.bootstrap.architecture.mvi

import androidx.annotation.StringRes

abstract class MviViewEvent {
    data class ShowToastStr(val message: String) : MviViewEvent()
    data class ShowToastRes(@StringRes val message: Int) : MviViewEvent()
}