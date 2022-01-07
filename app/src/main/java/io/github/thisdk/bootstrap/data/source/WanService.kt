package io.github.thisdk.bootstrap.data.source


import io.github.thisdk.bootstrap.domain.Friend
import io.github.thisdk.bootstrap.domain.Result
import retrofit2.http.GET

interface WanService {

    @GET("/friend/json")
    suspend fun friend(): Result<List<Friend>>

}