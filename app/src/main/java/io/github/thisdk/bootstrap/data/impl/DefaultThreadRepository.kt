package io.github.thisdk.bootstrap.data.impl

import io.github.thisdk.bootstrap.data.ThreadRepository
import io.github.thisdk.bootstrap.data.source.ThreadService
import javax.inject.Inject

class DefaultThreadRepository @Inject constructor() : ThreadRepository {

    @Inject
    lateinit var threadService: ThreadService

    override suspend fun fetchThreadData(fid: Int, page: Int): String {
        return threadService.thread(fid, page).string()
    }

}