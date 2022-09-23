package dev.nmgalo.repo.presentation.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.nmgalo.repo.R
import dev.nmgalo.repo.databinding.FragmentSearchBinding
import dev.nmgalo.repo.databinding.SearchViewBinding
import dev.nmgalo.repo.presentation.common.BaseFragment
import dev.nmgalo.repo.presentation.utils.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchViewModel>(R.layout.fragment_search) {

    private val adapter = RepoListAdapter()
    private var binding by autoCleared<FragmentSearchBinding>()
    override val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        binding.onViewBind()
    }

    private fun FragmentSearchBinding.onViewBind() {
        val layoutManager = GridLayoutManager(context, SEARCH_REPO_SPAN_COUNT)
        usersList.layoutManager = layoutManager
        usersList.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.searchResultStateFlow.collect {
                loader.hide()
                adapter.submitList(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.errorState.collect {
                loader.hide()
                if (it) errorDialog.showAlertErrorDialog()
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.loading.collectLatest {
                if (it) loader.show() else loader.hide()
            }
        }

        usersList.addOnScrollListener(AdapterScrollListener(layoutManager) {
            viewModel.fetchNext()
        })

        val search =
            SearchViewBinding.bind(toolbar.root.menu.findItem(R.id.search).actionView!!).search

        lifecycleScope.launch {
            search.textChanges().collect(viewModel::searchQuery::set)
        }
    }

    companion object {
        const val SEARCH_REPO_SPAN_COUNT = 2
    }
}
