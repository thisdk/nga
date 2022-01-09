package io.github.thisdk.nga.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.thisdk.nga.dao.CategoryDao
import io.github.thisdk.nga.dao.ForumDao
import io.github.thisdk.nga.domain.Category
import io.github.thisdk.nga.domain.Forum

@Database(entities = [Category::class, Forum::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun forumDao(): ForumDao
}