package dev.nmgalo.repo.presentation.utils

import dev.nmgalo.repo.domain.search.model.RepoDetails
import dev.nmgalo.repo.presentation.favourites.FavouritesUIModel
import dev.nmgalo.repo.presentation.repo.Repo

fun RepoDetails.toUIModel(onClick: (String, String) -> Unit) = Repo(
    id = this.repositoryName.hashCode().toLong(),
    owner = this.owner,
    avatar = this.avatar,
    repositoryName = this.repositoryName,
    onClick = { onClick(this.owner, this.repositoryName) }
)

fun RepoDetails.toFavouritesUIModel(onClick: (String, String) -> Unit) = FavouritesUIModel(
    id = this.repositoryName.hashCode().toLong(),
    owner = this.owner,
    avatar = this.avatar,
    repositoryName = this.repositoryName,
    onClick = { onClick(this.owner, this.repositoryName) }
)
