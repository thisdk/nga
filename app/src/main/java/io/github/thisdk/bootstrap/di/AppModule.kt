package io.github.thisdk.bootstrap.di

import android.content.Context
import android.graphics.Bitmap
import coil.ImageLoader
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.thisdk.bootstrap.common.NgaInterceptor
import io.github.thisdk.bootstrap.config.AppConfig
import io.github.thisdk.bootstrap.data.ThreadRepository
import io.github.thisdk.bootstrap.data.impl.DefaultThreadRepository
import io.github.thisdk.bootstrap.data.source.ThreadService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpClientModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(NgaInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    @ExperimentalSerializationApi
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object ImageLoaderModule {
    @Singleton
    @Provides
    fun provideImageLoader(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient
    ): ImageLoader {
        return ImageLoader.Builder(context)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .okHttpClient(okHttpClient)
            .build()
    }
}

@Module
@InstallIn(ViewModelComponent::class)
object ThreadServiceModule {
    @Provides
    fun provideThreadService(retrofit: Retrofit): ThreadService {
        return retrofit.create(ThreadService::class.java)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class ThreadRepositoryModule {
    @Binds
    abstract fun bindThreadRepository(threadRepository: DefaultThreadRepository): ThreadRepository
}