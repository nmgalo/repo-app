package dev.nmgalo.repo.presentation.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.nmgalo.repo.R
import dev.nmgalo.repo.databinding.FragmentFavouritesBinding
import dev.nmgalo.repo.presentation.common.BaseFragment
import dev.nmgalo.repo.presentation.utils.autoCleared

@AndroidEntryPoint
class FavouritesFragment : BaseFragment<FavouritesViewModel>(R.layout.fragment_favourites) {

    override val viewModel: FavouritesViewModel by viewModels()
    private var binding by autoCleared<FragmentFavouritesBinding>()
    private val adapter = FavouritesListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavouritesBinding.bind(view)
        binding.favouriteRepositoriesList.layoutManager = LinearLayoutManager(context)
        binding.favouriteRepositoriesList.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.favouritesState.collect(adapter::submitList)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.get()
    }
}
