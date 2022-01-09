package io.github.thisdk.nga.ui.home.forum

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
class ForumViewModel @Inject constructor(
    private val appDatabase: AppDatabase
) : ViewModel() {

    private val _viewStates = MutableLiveData(ForumViewState())
    val viewStates = _viewStates.asLiveData()

    private val _viewEvents: SingleLiveEvents<ForumViewEvent> = SingleLiveEvents()
    val viewEvents = _viewEvents.asLiveData()

    fun dispatch(action: ForumViewAction) {
        when (action) {
            is ForumViewAction.QueryForum -> queryForum(action.cid)
        }
    }

    private fun queryForum(cid: String) {
        viewModelScope.launch {
            flow {
                emit(appDatabase.forumDao().queryAllByCid(cid))
            }.onEach {
                _viewStates.setState { copy(forum = it) }
            }.collect()
        }

    }

}