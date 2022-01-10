package io.github.thisdk.nga

import android.app.Application
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

    override fun onCreate() {
        super.onCreate()

        runBlocking(Dispatchers.IO) {

            if (appDatabase.categoryDao().queryAll().isNotEmpty()) return@runBlocking

            val category = assets.open("category.json").bufferedReader().use {
                it.readText()
            }
            appDatabase.categoryDao().insertAll(*Json.decodeFromString<Array<Category>>(category))

            val forum = assets.open("forum.json").bufferedReader().use {
                it.readText()
            }
            appDatabase.forumDao().insertAll(*Json.decodeFromString<Array<Forum>>(forum))

        }

    }

}