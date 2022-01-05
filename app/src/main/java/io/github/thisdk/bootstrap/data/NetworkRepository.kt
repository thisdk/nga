package io.github.thisdk.bootstrap.data

import kotlinx.coroutines.delay
import javax.inject.Inject

class NetworkRepository @Inject constructor() {

    suspend fun getData1(): String {
        delay(2000)
        return "Hello"
    }


    suspend fun getData2(): String {
        delay(1000)
        return "Hello Hello"
    }

}