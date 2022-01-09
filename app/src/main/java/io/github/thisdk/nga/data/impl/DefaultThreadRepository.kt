package io.github.thisdk.nga.data.impl

import io.github.thisdk.nga.data.ThreadRepository
import io.github.thisdk.nga.data.source.ThreadService
import javax.inject.Inject

class DefaultThreadRepository @Inject constructor() : ThreadRepository {

    @Inject
    lateinit var threadService: ThreadService

    override suspend fun fetchThreadData(fid: Int, page: Int): String {
        return threadService.thread(fid, page).string()
    }

}