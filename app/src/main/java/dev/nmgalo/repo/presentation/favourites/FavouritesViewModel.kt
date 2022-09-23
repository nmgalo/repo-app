package dev.nmgalo.repo.presentation.favourites

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.repo.data.common.AppDispatchers
import dev.nmgalo.repo.data.common.Dispatcher
import dev.nmgalo.repo.domain.repo.GetFavouriteReposUseCase
import dev.nmgalo.repo.presentation.common.BaseViewModel
import dev.nmgalo.repo.presentation.utils.toFavouritesUIModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    @Dispatcher(AppDispatchers.IO)
    private val ioDispatcher: CoroutineDispatcher,
    private val getFavouriteReposUseCase: GetFavouriteReposUseCase
) : BaseViewModel() {

    private val _favouritesState = MutableStateFlow<List<FavouritesUIModel>>(emptyList())
    val favouritesState = _favouritesState.asStateFlow()

    init {
        viewModelScope.launch(ioDispatcher) {
            _favouritesState.value =
                getFavouriteReposUseCase.execute().map {
                    it.toFavouritesUIModel { owner, repositoryName ->
                        FavouritesFragmentDirections
                            .actionFavouritesFragmentToRepoDetailsFragment(owner, repositoryName)
                            .navigate()
                    }
                }
        }
    }
}
