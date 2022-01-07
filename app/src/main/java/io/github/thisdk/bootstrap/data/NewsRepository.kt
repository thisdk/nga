package io.github.thisdk.bootstrap.data

import io.github.thisdk.bootstrap.domain.Friend

interface NewsRepository {
    suspend fun fetchUser(): String
    suspend fun fetchData(): List<Friend>
}

