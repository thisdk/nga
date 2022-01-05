package io.github.thisdk.bootstrap.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.thisdk.bootstrap.data.NetworkRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    private val _message = MutableLiveData<String>()

    val text: LiveData<String> = _text
    val message: LiveData<String> = _message

    fun test() {
        viewModelScope.launch {
            delay(2000)
            _text.value = networkRepository.getData1()
            _message.value = "message"
            delay(1000)
            _text.value = networkRepository.getData2()
        }
    }

}