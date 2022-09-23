package dev.nmgalo.repo.presentation.utils

import dev.nmgalo.repo.domain.search.model.SearchReposDomainModel
import dev.nmgalo.repo.presentation.search.Repo

fun SearchReposDomainModel.toUIModel(onClick: (Long) -> Unit) = Repo(
    id = this.id,
    userName = this.userName,
    avatar = this.avatar,
    repositoryName = this.repositoryName,
    onClick = { onClick(this.id) }
)
