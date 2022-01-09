package io.github.thisdk.nga.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val cid: String,
    @ColumnInfo val name: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (id != other.id) return false
        if (cid != other.cid) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + cid.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}
