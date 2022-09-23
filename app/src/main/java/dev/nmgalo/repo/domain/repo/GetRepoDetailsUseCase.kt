package dev.nmgalo.repo.domain.repo

import javax.inject.Inject

class GetRepoDetailsUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) {
    suspend fun execute(owner: String, name: String) = repoRepository.getDetails(owner, name)
}
