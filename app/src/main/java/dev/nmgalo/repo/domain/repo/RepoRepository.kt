package dev.nmgalo.repo.domain.repo

import dev.nmgalo.repo.domain.search.model.RepoDetails


interface RepoRepository {
    suspend fun getDetails(owner: String, name: String): RepoDetails
}
