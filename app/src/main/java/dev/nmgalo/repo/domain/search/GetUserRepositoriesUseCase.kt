package dev.nmgalo.repo.domain.search

import dev.nmgalo.repo.domain.search.model.RepoDetails
import dev.nmgalo.repo.domain.search.model.SearchReposRequest
import javax.inject.Inject

class GetUserRepositoriesUseCase @Inject constructor(
    private val repo: SearchRepository,
) {
    suspend fun get(userName: String, page: Int, perPage: Int): List<RepoDetails> =
        repo.search(SearchReposRequest(userName, page, perPage))
}
