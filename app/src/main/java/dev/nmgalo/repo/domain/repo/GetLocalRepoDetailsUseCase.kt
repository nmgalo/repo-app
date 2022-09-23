package dev.nmgalo.repo.domain.repo

import dev.nmgalo.repo.domain.search.model.RepoDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalRepoDetailsUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) {
    suspend fun execute(owner: String, name: String): Flow<RepoDetails?> =
        repoRepository.findRepoByAuthorAndRepoName(owner, name)
}
