package io.github.thisdk.bootstrap.domain

import kotlinx.serialization.Serializable

@Serializable
data class Friend(
    val category: String,
    val icon: String,
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)