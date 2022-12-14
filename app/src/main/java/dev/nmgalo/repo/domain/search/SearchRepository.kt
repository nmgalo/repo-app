package dev.nmgalo.repo.domain.search

import dev.nmgalo.repo.domain.search.model.RepoDetails
import dev.nmgalo.repo.domain.search.model.SearchReposRequest

interface SearchRepository {
    suspend fun search(searchReposRequest: SearchReposRequest): List<RepoDetails>
}
