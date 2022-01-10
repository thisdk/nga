package io.github.thisdk.nga.ui.home

import androidx.annotation.StringRes
import io.github.thisdk.nga.domain.Category

sealed class HomeViewAction {
    data class PageChange(val currentItem: Int) : HomeViewAction()
    object QueryCategory : HomeViewAction()
}

sealed class HomeViewEvent {
    data class ShowToastStr(val message: String) : HomeViewEvent()
    data class ShowToastRes(@StringRes val message: Int) : HomeViewEvent()
}

data class HomeViewState(
    val currentItem: Int = 0,
    val category: List<Category> = listOf()
)