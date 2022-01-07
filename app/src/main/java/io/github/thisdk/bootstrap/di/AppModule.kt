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
import io.github.thisdk.bootstrap.data.NewsRepository
import io.github.thisdk.bootstrap.data.NewsRepositoryImpl
import io.github.thisdk.bootstrap.data.source.WanService
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
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
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
            .baseUrl("https://www.wanandroid.com")
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
abstract class NewsRepositoryModule {
    @Binds
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}

@Module
@InstallIn(ViewModelComponent::class)
object BaiduServiceModule {

    @Provides
    fun provideBaiduService(retrofit: Retrofit): WanService {
        return retrofit.create(WanService::class.java)
    }
}


