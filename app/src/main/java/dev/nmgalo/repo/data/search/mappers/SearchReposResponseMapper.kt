package dev.nmgalo.repo.data.search.mappers

import dev.nmgalo.repo.data.model.search.SearchReposResponse
import dev.nmgalo.repo.domain.search.model.SearchReposDomainModel

fun SearchReposResponse.toDomainModel() = SearchReposDomainModel(
    id = this.id,
    userName = this.owner.login,
    avatar = this.owner.avatarURL,
    repositoryName = this.name
)
