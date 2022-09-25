package dev.nmgalo.repo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Query("SELECT * FROM repo ORDER BY id DESC")
    fun getAll(): List<RepoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: RepoEntity)

    @Query("SELECT * FROM repo WHERE author = :owner AND repository_name = :name")
    fun findRepoByAuthorAndRepoName(owner: String, name: String): Flow<RepoEntity?>

    @Query("DELETE FROM repo where author = :owner AND repository_name = :name")
    fun deleteByOwnerAndName(owner: String, name: String)
}
