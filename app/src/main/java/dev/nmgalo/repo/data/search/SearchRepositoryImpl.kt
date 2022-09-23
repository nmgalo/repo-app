package dev.nmgalo.repo.data.search

import dev.nmgalo.repo.data.GithubNetworkDataSource
import dev.nmgalo.repo.data.search.mappers.toDomainModel
import dev.nmgalo.repo.domain.search.SearchRepository
import dev.nmgalo.repo.domain.search.model.RepoDetails
import dev.nmgalo.repo.domain.search.model.SearchReposRequest
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val network: GithubNetworkDataSource
) : SearchRepository {
    override suspend fun search(searchReposRequest: SearchReposRequest): List<RepoDetails> {
        return network.getUserRepositories(searchReposRequest).map { it.toDomainModel() }
    }
}
