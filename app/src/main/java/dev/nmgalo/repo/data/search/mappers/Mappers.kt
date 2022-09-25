package dev.nmgalo.repo.data.search.mappers

import dev.nmgalo.repo.data.database.RepoEntity
import dev.nmgalo.repo.data.model.search.RepoDetailDTO
import dev.nmgalo.repo.domain.search.model.RepoDetails

fun RepoDetailDTO.toDomainModel() = RepoDetails(
    owner = this.owner.login,
    avatar = this.owner.avatarURL,
    repositoryName = this.name
)

fun RepoEntity.toDomainModel() = RepoDetails(
    owner = this.author,
    avatar = this.avatar,
    repositoryName = this.repositoryName
)
