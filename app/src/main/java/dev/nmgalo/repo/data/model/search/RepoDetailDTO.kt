package dev.nmgalo.repo.data.model.search

import kotlinx.serialization.Serializable

@Serializable
data class RepoDetailDTO(
    val id: Long,
    val name: String,
    val owner: RepoOwnerDTO
)
