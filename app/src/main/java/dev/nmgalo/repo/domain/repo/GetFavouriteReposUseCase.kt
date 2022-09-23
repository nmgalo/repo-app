package dev.nmgalo.repo.domain.repo

import javax.inject.Inject

class GetFavouriteReposUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) {
    suspend fun execute() = repoRepository.getAllFavouriteRepos()
}
