package dev.nmgalo.repo.presentation.repo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.repo.data.common.AppDispatchers
import dev.nmgalo.repo.data.common.Dispatcher
import dev.nmgalo.repo.domain.repo.GetRepoDetailsUseCase
import dev.nmgalo.repo.presentation.common.BaseViewModel
import dev.nmgalo.repo.presentation.utils.getValue
import dev.nmgalo.repo.presentation.utils.toUIModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    @Dispatcher(AppDispatchers.IO)
    private val ioDispatcher: CoroutineDispatcher,
    private val getRepoDetailsUseCase: GetRepoDetailsUseCase,
    state: SavedStateHandle
) : BaseViewModel() {

    private val _repoState = MutableStateFlow<RepoState>(RepoState.Loading)
    val repoState = _repoState.asStateFlow()

    init {
        getRepositoryDetails(
            owner = state.getValue("owner"),
            name = state.getValue("name")
        )
    }

    private fun getRepositoryDetails(owner: String, name: String) {
        viewModelScope.launch(ioDispatcher) {
            getRepoDetailsUseCase.execute(owner, name).let {
                _repoState.value = RepoState.Success(it.toUIModel { _, _ -> })
            }
        }
    }
}
