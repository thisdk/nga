package io.github.thisdk.bootstrap.data.source

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ThreadService {

    @GET("/thread.php?__output=8")
    suspend fun thread(@Query("fid") fid: Int, @Query("page") page: Int): ResponseBody

}