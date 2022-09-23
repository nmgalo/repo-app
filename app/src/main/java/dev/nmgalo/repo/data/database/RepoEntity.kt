package dev.nmgalo.repo.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "repo",
    indices = [Index("author"), Index("repository_name")]
)
data class RepoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "avatar")
    val avatar: String,
    @ColumnInfo(name = "repository_name")
    val repositoryName: String
)
