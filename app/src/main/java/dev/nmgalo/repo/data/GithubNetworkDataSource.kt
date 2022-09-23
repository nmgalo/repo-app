package dev.nmgalo.repo.data

import dev.nmgalo.repo.data.model.search.RepoDetailDTO
import dev.nmgalo.repo.domain.search.model.SearchReposRequest

interface GithubNetworkDataSource {
    suspend fun getUserRepositories(searchReposRequest: SearchReposRequest): List<RepoDetailDTO>
    suspend fun getRepositoryDetails(owner: String, name: String): RepoDetailDTO
}
