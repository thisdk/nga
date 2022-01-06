package io.github.thisdk.bootstrap.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.github.thisdk.bootstrap.architecture.ktx.asLiveData
import io.github.thisdk.bootstrap.architecture.ktx.setState
import io.github.thisdk.bootstrap.architecture.mvi.MviViewEvent
import io.github.thisdk.bootstrap.architecture.mvi.MviViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : MviViewModel<HomeViewAction, HomeViewEvent>() {

    private val _viewStates = MutableLiveData(HomeViewState())
    val viewStates = _viewStates.asLiveData()

    override fun dispatch(action: HomeViewAction) {
        when (action) {
            is HomeViewAction.FetchData -> getUiData()
            is HomeViewAction.Value1 -> setValue1()
        }
    }

    private fun getUiData() {
        _viewEvents.setEvent(MviViewEvent.ShowToastRes)
    }

    private fun setValue1() {
        viewModelScope.launch {
            delay(2000)
            _viewStates.setState { copy(value1 = "GO GO GO Value1") }
        }
    }

}