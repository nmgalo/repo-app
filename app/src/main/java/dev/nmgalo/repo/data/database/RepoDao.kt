package dev.nmgalo.repo.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Query("SELECT * FROM repo")
    fun getAll(): List<RepoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: RepoEntity)

    @Query("SELECT * FROM repo WHERE author = :owner AND repository_name = :name")
    fun findRepoByAuthorAndRepoName(owner: String, name: String): Flow<RepoEntity?>

    @Query("DELETE FROM repo where author = :owner AND repository_name = :name")
    fun deleteByOwnerAndName(owner: String, name: String)
}
