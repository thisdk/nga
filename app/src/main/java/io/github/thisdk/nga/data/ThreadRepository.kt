package io.github.thisdk.nga.data

interface ThreadRepository {

    suspend fun fetchThreadData(fid: Int, page: Int): String

}