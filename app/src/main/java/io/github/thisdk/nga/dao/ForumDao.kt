package io.github.thisdk.nga.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.thisdk.nga.domain.Forum

@Dao
interface ForumDao {

    @Query("SELECT * FROM forum WHERE cid IN (:cid)")
    suspend fun queryAllByCid(cid: String): Array<Forum>

    @Insert
    suspend fun insertAll(vararg forum: Forum)

    @Delete
    suspend fun delete(forum: Forum)

}