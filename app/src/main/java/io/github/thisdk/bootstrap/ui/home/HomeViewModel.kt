package io.github.thisdk.bootstrap.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.thisdk.bootstrap.architecture.component.SingleLiveEvents
import io.github.thisdk.bootstrap.architecture.ktx.asLiveData
import io.github.thisdk.bootstrap.architecture.ktx.setState
import io.github.thisdk.bootstrap.data.NewsRepository
import kotlinx.coroutines.delay
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
            is HomeViewAction.Value1 -> setValue1()
            is HomeViewAction.Value2 -> setValue2()
            is HomeViewAction.Value3 -> setValue3()
            is HomeViewAction.LoadImage -> loadImage()
        }
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

    private fun setValue2() {
        viewModelScope.launch {
            flow {
                for (i in 0 until 50) {
                    delay(500)
                    _viewStates.setState { copy(value2 = "发射元素 $i") }
                    emit("value $i")
                }
            }.buffer(20).onEach {
                _viewStates.setState { copy(value3 = "收到元素 $it") }
                delay(2000)
            }.collect()
        }
    }


    private fun setValue3() {
        viewModelScope.launch {
            flow {
                newsRepository.fetchData().forEach {
                    emit(it)
                    delay(1000)
                }
            }.onEach {
                _viewStates.setState { copy(value3 = it.name) }
            }.catch {
                it.printStackTrace()
                _viewEvents.setEvent(HomeViewEvent.ShowToastStr(message = it.message ?: "message"))
            }.collect()
        }
    }

    private fun loadImage() {
        viewModelScope.launch {
            _viewStates.setState { copy(url = "https://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fup.enterdesk.com%252Fedpic%252F76%252F7b%252F57%252F767b578c5e68a890cf98b5b7e002aca8.jpeg%26refer%3Dhttp%253A%252F%252Fup.enterdesk.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1644132412%26t%3Dc7aba0f8f9717818df60cd74e05ec08c&thumburl=https%3A%2F%2Fimg1.baidu.com%2Fit%2Fu%3D818940727%2C1723281244%26fm%3D26%26fmt%3Dauto") }
        }
    }

}