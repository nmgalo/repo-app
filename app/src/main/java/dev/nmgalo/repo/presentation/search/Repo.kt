package dev.nmgalo.repo.presentation.search

data class Repo(
    val id: Long,
    val userName: String,
    val avatar: String,
    val repositoryName: String,
    val onClick: () -> Unit
)
