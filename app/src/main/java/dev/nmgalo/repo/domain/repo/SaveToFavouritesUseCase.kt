package dev.nmgalo.repo.domain.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SaveToFavouritesUseCase @Inject constructor(
    private val getLocalRepoDetailsUseCase: GetLocalRepoDetailsUseCase,
    private val repoRepository: RepoRepository
) {
    suspend fun execute(author: String, repositoryName: String): Flow<Unit> =
        getLocalRepoDetailsUseCase.execute(author, repositoryName)
            .map {
                if (it == null) {
                    repoRepository.getDetails(author, repositoryName).let { repoDetails ->
                        repoRepository.saveToFavourites(repoDetails)
                    }
                } else {
                    repoRepository.removeFromFavourites(it)
                }
            }
}
