package io.github.thisdk.nga.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.thisdk.nga.domain.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun queryAllFlow(): Flow<List<Category>>

    @Query("SELECT * FROM category")
    suspend fun queryAll(): List<Category>

    @Insert
    suspend fun insertAll(vararg category: Category)

    @Delete
    suspend fun delete(category: Category)

}