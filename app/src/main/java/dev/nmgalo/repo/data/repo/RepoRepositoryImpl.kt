package dev.nmgalo.repo.data.repo

import dev.nmgalo.repo.data.GithubNetworkDataSource
import dev.nmgalo.repo.data.database.RepoDao
import dev.nmgalo.repo.data.database.RepoEntity
import dev.nmgalo.repo.data.search.mappers.toDomainModel
import dev.nmgalo.repo.domain.repo.RepoRepository
import dev.nmgalo.repo.domain.search.model.RepoDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val network: GithubNetworkDataSource,
    private val repoDao: RepoDao
) : RepoRepository {

    override suspend fun getDetails(owner: String, name: String): RepoDetails {
        return network.getRepositoryDetails(owner, name).toDomainModel()
    }

    override suspend fun saveToFavourites(repo: RepoDetails) {
        repoDao.insert(
            RepoEntity(
                author = repo.author,
                avatar = repo.avatar,
                repositoryName = repo.repositoryName
            )
        )
    }

    override suspend fun removeFromFavourites(repo: RepoDetails) {
        repoDao.deleteByOwnerAndName(owner = repo.author, name = repo.repositoryName)
    }

    override suspend fun findRepoByAuthorAndRepoName(owner: String, name: String) =
        repoDao.findRepoByAuthorAndRepoName(owner, name).map { it?.toDomainModel() }
}
