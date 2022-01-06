package io.github.thisdk.bootstrap.data

interface NewsRepository {
    suspend fun fetchUser(): String
}

