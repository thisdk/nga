package io.github.thisdk.bootstrap.data

import kotlinx.coroutines.delay
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor() : NewsRepository {

    override suspend fun fetchUser(): String {
        delay(2000)
        return "NewsRepositoryImpl"
    }

}