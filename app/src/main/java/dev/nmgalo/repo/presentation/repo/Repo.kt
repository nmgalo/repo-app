package dev.nmgalo.repo.presentation.repo

data class Repo(
    val id: Long,
    val owner: String,
    val avatar: String,
    val repositoryName: String,
    val onClick: () -> Unit
)
