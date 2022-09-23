package dev.nmgalo.repo.presentation.search

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.repo.data.common.AppDispatchers
import dev.nmgalo.repo.data.common.Dispatcher
import dev.nmgalo.repo.domain.search.GetUserRepositoriesUseCase
import dev.nmgalo.repo.presentation.common.BaseViewModel
import dev.nmgalo.repo.presentation.utils.toUIModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    @Dispatcher(AppDispatchers.IO)
    private val ioDispatcher: CoroutineDispatcher,
    private val getUserRepositoriesUseCase: GetUserRepositoriesUseCase
) : BaseViewModel() {

    private val currentPage = MutableStateFlow(DEFAULT_CURRENT_PAGE)

    private val _searchResultStateFlow = MutableStateFlow<List<Repo>>(emptyList())
    val searchResultStateFlow = _searchResultStateFlow.asStateFlow()

    private val _errorState = MutableStateFlow(false)
    val errorState = _errorState.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    var searchQuery: String = DEFAULT_SEARCH_USERNAME
        set(value) {
            field = value
            _searchResultStateFlow.value = emptyList()
            currentPage.value = DEFAULT_CURRENT_PAGE
            search(currentPage.value)
        }

    init {
        search(currentPage.value)
    }

    fun search(page: Int) {
        viewModelScope.launch(ioDispatcher + errorHandler()) {
            _loading.value = true
            getUserRepositoriesUseCase.get(searchQuery, page, PER_PAGE).let {
                _searchResultStateFlow.value += it.map { item ->
                    item.toUIModel { repoId ->
                        SearchFragmentDirections
                            .actionSearchFragmentToRepoDetailsFragment(repoId)
                            .navigate()
                    }
                }
                _loading.value = false
            }
        }
    }

    private fun errorHandler() = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> _errorState.value = true
            else -> error(exception)
        }
    }

    fun fetchNext() {
        search(++currentPage.value)
    }

    companion object {
        const val PER_PAGE = 20
        const val DEFAULT_SEARCH_USERNAME = "JakeWharton"
        const val DEFAULT_CURRENT_PAGE = 1
    }
}
