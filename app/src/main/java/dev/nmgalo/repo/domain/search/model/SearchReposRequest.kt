package dev.nmgalo.repo.domain.search.model

data class SearchReposRequest(
    val owner: String,
    val page: Int,
    val perPage: Int
)
