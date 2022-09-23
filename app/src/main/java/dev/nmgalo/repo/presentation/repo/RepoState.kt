package dev.nmgalo.repo.presentation.repo

sealed class RepoState {
    object Loading : RepoState()

    data class Success(
        val data: Repo
    ) : RepoState()

    object Error : RepoState()
}
