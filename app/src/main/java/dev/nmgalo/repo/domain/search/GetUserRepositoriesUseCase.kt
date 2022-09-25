package dev.nmgalo.repo.domain.search

import dev.nmgalo.repo.domain.search.model.RepoDetails
import dev.nmgalo.repo.domain.search.model.SearchReposRequest
import javax.inject.Inject

class GetUserRepositoriesUseCase @Inject constructor(
    private val repo: SearchRepository,
) {

    private var searchKeyword: String = ""

    suspend fun get(owner: String, page: Int, perPage: Int): List<RepoDetails> {
        searchKeyword = owner
        return repo.search(SearchReposRequest(searchKeyword, page, perPage))
    }
}
