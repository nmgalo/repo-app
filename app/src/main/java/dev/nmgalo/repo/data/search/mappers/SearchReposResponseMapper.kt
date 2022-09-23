package dev.nmgalo.repo.data.search.mappers

import dev.nmgalo.repo.data.model.search.RepoDetailDTO
import dev.nmgalo.repo.domain.search.model.RepoDetails

fun RepoDetailDTO.toDomainModel() = RepoDetails(
    id = this.id,
    userName = this.owner.login,
    avatar = this.owner.avatarURL,
    repositoryName = this.name
)
