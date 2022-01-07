package io.github.thisdk.bootstrap.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.thisdk.bootstrap.architecture.component.SingleLiveEvents
import io.github.thisdk.bootstrap.architecture.ktx.asLiveData
import io.github.thisdk.bootstrap.architecture.ktx.setState
import io.github.thisdk.bootstrap.data.ThreadRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val threadRepository: ThreadRepository
) : ViewModel() {

    private val _viewStates = MutableLiveData(HomeViewState())
    val viewStates = _viewStates.asLiveData()

    private val _viewEvents: SingleLiveEvents<HomeViewEvent> = SingleLiveEvents()
    val viewEvents = _viewEvents.asLiveData()

    fun dispatch(action: HomeViewAction) {
        when (action) {
            is HomeViewAction.FetchThreadData -> fetchThreadData(action.fid, action.page)
        }
    }

    private fun fetchThreadData(fid: Int, page: Int) {
        viewModelScope.launch {
            flow {
                emit(threadRepository.fetchThreadData(fid, page))
            }.onStart {
                _viewEvents.setEvent(HomeViewEvent.ShowToastStr(message = "开始请求"))
            }.onEach {
                _viewStates.setState { copy(url = "OK") }
            }.onCompletion {
                _viewEvents.setEvent(HomeViewEvent.ShowToastStr(message = "请求完毕"))
            }.catch {
                it.printStackTrace()
            }.collect()
        }
    }

}