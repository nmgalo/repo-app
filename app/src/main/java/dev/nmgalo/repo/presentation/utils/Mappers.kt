package dev.nmgalo.repo.presentation.utils

import dev.nmgalo.repo.domain.search.model.RepoDetails
import dev.nmgalo.repo.presentation.repo.Repo

fun RepoDetails.toUIModel(onClick: (String, String) -> Unit) = Repo(
    id = this.id,
    userName = this.userName,
    avatar = this.avatar,
    repositoryName = this.repositoryName,
    onClick = { onClick(this.userName, this.repositoryName) }
)
