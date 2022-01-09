package io.github.thisdk.nga.ui.home

import androidx.annotation.StringRes
import io.github.thisdk.nga.domain.Category

sealed class HomeViewAction {
    object QueryCategory : HomeViewAction()
}

sealed class HomeViewEvent {
    data class ShowToastStr(val message: String) : HomeViewEvent()
    data class ShowToastRes(@StringRes val message: Int) : HomeViewEvent()
}

data class HomeViewState(
    val category: Array<Category> = arrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HomeViewState

        if (!category.contentEquals(other.category)) return false

        return true
    }

    override fun hashCode(): Int {
        return category.contentHashCode()
    }

}