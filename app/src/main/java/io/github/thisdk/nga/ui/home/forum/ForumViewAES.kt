package io.github.thisdk.nga.ui.home.forum

import androidx.annotation.StringRes
import io.github.thisdk.nga.domain.Forum

sealed class ForumViewAction {
    data class QueryForum(val cid: String) : ForumViewAction()
}

sealed class ForumViewEvent {
    data class ShowToastStr(val message: String) : ForumViewEvent()
    data class ShowToastRes(@StringRes val message: Int) : ForumViewEvent()
}

data class ForumViewState(
    val forum: Array<Forum> = arrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ForumViewState

        if (!forum.contentEquals(other.forum)) return false

        return true
    }

    override fun hashCode(): Int {
        return forum.contentHashCode()
    }

}