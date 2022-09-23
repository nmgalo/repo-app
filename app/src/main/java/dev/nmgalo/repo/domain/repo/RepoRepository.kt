package dev.nmgalo.repo.domain.repo

import dev.nmgalo.repo.domain.search.model.RepoDetails
import kotlinx.coroutines.flow.Flow


interface RepoRepository {
    suspend fun getDetails(owner: String, name: String): RepoDetails
    suspend fun saveToFavourites(repo: RepoDetails)
    suspend fun removeFromFavourites(repo: RepoDetails)
    suspend fun findRepoByAuthorAndRepoName(owner: String, name: String): Flow<RepoDetails?>
}
