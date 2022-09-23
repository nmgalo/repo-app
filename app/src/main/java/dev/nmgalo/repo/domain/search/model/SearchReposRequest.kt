package dev.nmgalo.repo.domain.search.model

data class SearchReposRequest(
    val userName: String,
    val page: Int,
    val perPage: Int
)
