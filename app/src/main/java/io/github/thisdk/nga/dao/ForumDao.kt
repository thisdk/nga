package io.github.thisdk.nga.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.thisdk.nga.domain.Forum
import kotlinx.coroutines.flow.Flow

@Dao
interface ForumDao {

    @Query("SELECT * FROM forum WHERE cid IN (:cid)")
    fun queryAllByCidFlow(cid: String): Flow<List<Forum>>

    @Insert
    suspend fun insertAll(vararg forum: Forum)

    @Delete
    suspend fun delete(forum: Forum)

}