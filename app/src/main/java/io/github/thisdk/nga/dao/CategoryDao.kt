package io.github.thisdk.nga.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.thisdk.nga.domain.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    suspend fun queryAll(): Array<Category>

    @Insert
    suspend fun insertAll(vararg category: Category)

    @Delete
    suspend fun delete(category: Category)

}