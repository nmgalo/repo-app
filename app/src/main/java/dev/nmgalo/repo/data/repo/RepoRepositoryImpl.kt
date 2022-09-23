package dev.nmgalo.repo.data.repo

import dev.nmgalo.repo.data.GithubNetworkDataSource
import dev.nmgalo.repo.data.search.mappers.toDomainModel
import dev.nmgalo.repo.domain.repo.RepoRepository
import dev.nmgalo.repo.domain.search.model.RepoDetails
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val network: GithubNetworkDataSource
) : RepoRepository {
    override suspend fun getDetails(owner: String, name: String): RepoDetails {
        return network.getRepositoryDetails(owner, name).toDomainModel()
    }
}
