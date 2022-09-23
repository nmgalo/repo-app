package dev.nmgalo.repo.domain.search.model

data class RepoDetails(
    val id: Long,
    val userName: String,
    val avatar: String,
    val repositoryName: String
)
