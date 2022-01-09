package io.github.thisdk.nga

import android.app.Application
import coil.Coil
import coil.ImageLoader
import dagger.hilt.android.HiltAndroidApp
import io.github.thisdk.nga.db.AppDatabase
import io.github.thisdk.nga.domain.Category
import io.github.thisdk.nga.domain.Forum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var appDatabase: AppDatabase

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate() {
        super.onCreate()

        Coil.setImageLoader(imageLoader)

        writeToDatabase()

    }

    private fun writeToDatabase() {

        runBlocking(Dispatchers.IO) {

            if (appDatabase.categoryDao().queryAll().isNotEmpty()) return@runBlocking

            val category = assets.open("category.json").bufferedReader().use {
                it.readText()
            }
            val forum = assets.open("forum.json").bufferedReader().use {
                it.readText()
            }

            appDatabase.categoryDao().insertAll(*Json.decodeFromString<Array<Category>>(category))
            appDatabase.forumDao().insertAll(*Json.decodeFromString<Array<Forum>>(forum))

        }

    }

    override fun onTerminate() {
        super.onTerminate()
        appDatabase.close()
    }

}