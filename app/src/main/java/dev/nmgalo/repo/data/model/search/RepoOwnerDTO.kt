package dev.nmgalo.repo.data.model.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoOwnerDTO(
    val login: String,
    @SerialName("avatar_url")
    val avatarURL: String
)
