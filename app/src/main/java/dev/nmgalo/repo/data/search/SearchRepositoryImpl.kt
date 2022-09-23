package dev.nmgalo.repo.data.search

import dev.nmgalo.repo.data.GithubNetworkDataSource
import dev.nmgalo.repo.data.search.mappers.toDomainModel
import dev.nmgalo.repo.domain.search.SearchRepository
import dev.nmgalo.repo.domain.search.model.SearchReposDomainModel
import dev.nmgalo.repo.domain.search.model.SearchReposRequest
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val network: GithubNetworkDataSource
) : SearchRepository {
    override suspend fun search(searchReposRequest: SearchReposRequest): List<SearchReposDomainModel> {
        return network.getUserRepositories(searchReposRequest).map { it.toDomainModel() }
    }
}
