package io.github.thisdk.bootstrap.data

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.delay

@Module
@InstallIn(ActivityComponent::class)
class NetworkRepository {

    suspend fun getData1(): String {
        delay(2000)
        return "Hello"
    }


    suspend fun getData2(): String {
        delay(1000)
        return "Hello Hello"
    }

}