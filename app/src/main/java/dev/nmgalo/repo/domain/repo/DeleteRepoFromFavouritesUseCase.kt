package dev.nmgalo.repo.domain.repo

import dev.nmgalo.repo.domain.search.model.RepoDetails
import javax.inject.Inject

class DeleteRepoFromFavouritesUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) {

    suspend fun execute(repo: RepoDetails) {
        repoRepository.saveToFavourites(repo)
    }
}
