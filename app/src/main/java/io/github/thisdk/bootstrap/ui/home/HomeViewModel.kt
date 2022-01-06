package io.github.thisdk.bootstrap.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.thisdk.bootstrap.architecture.component.SingleLiveEvents
import io.github.thisdk.bootstrap.architecture.ktx.asLiveData
import io.github.thisdk.bootstrap.architecture.ktx.setState
import io.github.thisdk.bootstrap.data.NewsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _viewStates = MutableLiveData(HomeViewState())
    val viewStates = _viewStates.asLiveData()

    private val _viewEvents: SingleLiveEvents<HomeViewEvent> = SingleLiveEvents()
    val viewEvents = _viewEvents.asLiveData()

    fun dispatch(action: HomeViewAction) {
        when (action) {
            is HomeViewAction.FetchData -> getUiData()
            is HomeViewAction.Value1 -> setValue1()
        }
    }

    private fun getUiData() {
        _viewEvents.setEvent(HomeViewEvent.ShowToastStr(message = "Hello"))
    }

    private fun setValue1() {
        viewModelScope.launch {
            flow {
                emit(newsRepository.fetchUser())
            }.onStart {
                _viewEvents.setEvent(HomeViewEvent.ShowToastStr(message = "开始请求网络"))
            }.onEach {
                _viewStates.setState { copy(value1 = it) }
            }.onCompletion {
                _viewEvents.setEvent(HomeViewEvent.ShowToastStr(message = "请求完毕"))
            }.collect()
        }
    }

}