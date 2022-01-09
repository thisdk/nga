package io.github.thisdk.nga.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.thisdk.nga.architecture.component.SingleLiveEvents
import io.github.thisdk.nga.architecture.ktx.asLiveData
import io.github.thisdk.nga.architecture.ktx.setState
import io.github.thisdk.nga.db.AppDatabase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appDatabase: AppDatabase
) : ViewModel() {

    private val _viewStates = MutableLiveData(HomeViewState())
    val viewStates = _viewStates.asLiveData()

    private val _viewEvents: SingleLiveEvents<HomeViewEvent> = SingleLiveEvents()
    val viewEvents = _viewEvents.asLiveData()

    fun dispatch(action: HomeViewAction) {
        when (action) {
            is HomeViewAction.QueryCategory -> queryCategory()
            is HomeViewAction.PageChange -> pageChange(action.currentItem)
        }
    }

    private fun pageChange(currentItem: Int) {
        viewModelScope.launch {
            _viewStates.setState { copy(currentItem = currentItem) }
        }
    }

    private fun queryCategory() {
        viewModelScope.launch {
            flow {
                emit(appDatabase.categoryDao().queryAll())
            }.onEach {
                _viewStates.setState { copy(category = it) }
            }.collect()
        }

    }

}