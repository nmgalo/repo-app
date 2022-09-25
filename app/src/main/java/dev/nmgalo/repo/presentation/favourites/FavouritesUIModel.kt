package dev.nmgalo.repo.presentation.favourites

data class FavouritesUIModel(
    val id: Long,
    val owner: String,
    val avatar: String,
    val repositoryName: String,
    val onClick: () -> Unit
)
