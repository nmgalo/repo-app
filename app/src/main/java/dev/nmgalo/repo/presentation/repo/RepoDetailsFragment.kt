package dev.nmgalo.repo.presentation.repo

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import dev.nmgalo.repo.R
import dev.nmgalo.repo.databinding.FragmentRepoDetailsBinding
import dev.nmgalo.repo.presentation.common.BaseFragment
import dev.nmgalo.repo.presentation.utils.autoCleared
import dev.nmgalo.repo.presentation.utils.hide
import dev.nmgalo.repo.presentation.utils.show
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class RepoDetailsFragment @Inject constructor(

) : BaseFragment<RepoViewModel>(R.layout.fragment_repo_details) {

    override val viewModel: RepoViewModel by viewModels()
    private var binding by autoCleared<FragmentRepoDetailsBinding>()
    private val args: RepoDetailsFragmentArgs by navArgs()

    private lateinit var starMenu: MenuItem

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRepoDetailsBinding.bind(view)
        binding.onViewBind()
    }

    private fun FragmentRepoDetailsBinding.onViewBind() {

        lifecycleScope.launchWhenStarted {
            viewModel.repoState.collectLatest {
                repoGroup.isVisible = false
                when (it) {
                    RepoState.Error -> {
                        loader.hide()
                    }
                    RepoState.Loading -> loader.show()
                    is RepoState.Success -> {
                        loader.hide()
                        repoGroup.isVisible = true
                        repositoryOwnerName.text = it.data.owner
                        repositoryName.text = it.data.repositoryName
                        userProfileImageView.load(it.data.avatar) {
                            transformations(CircleCropTransformation())
                        }
                    }
                }
            }
        }

        toolbar.root.setNavigationOnClickListener { viewModel.popBackStack() }

        starMenu = toolbar.root.menu.findItem(R.id.star)
        starMenu.setOnMenuItemClickListener {
            viewModel.saveToFavourites(args.owner, args.name)
            true
        }

        lifecycleScope.launchWhenStarted {
            viewModel.isFavourite.collectLatest {
                starMenu.icon =
                    if (it) getDrawable(requireContext(), R.drawable.ic_star_filled) else
                        getDrawable(requireContext(), R.drawable.ic_star)
            }
        }
    }
}
