package io.github.thisdk.bootstrap.data

interface ThreadRepository {

    suspend fun fetchThreadData(fid: Int, page: Int): String

}