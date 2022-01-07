package io.github.thisdk.bootstrap.data

import io.github.thisdk.bootstrap.data.source.WanService
import io.github.thisdk.bootstrap.domain.Friend
import kotlinx.coroutines.delay
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor() : NewsRepository {

    @Inject
    lateinit var service: WanService

    override suspend fun fetchUser(): String {
        delay(2000)
        return "NewsRepositoryImpl"
    }

    override suspend fun fetchData(): List<Friend> {
        return service.friend().data
    }

}