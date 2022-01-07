package io.github.thisdk.bootstrap.domain

import kotlinx.serialization.Serializable

@Serializable
data class Result<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)

