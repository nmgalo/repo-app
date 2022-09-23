package dev.nmgalo.repo.presentation.repo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.nmgalo.repo.R
import dev.nmgalo.repo.databinding.FragmentRepoDetailsBinding
import dev.nmgalo.repo.presentation.common.BaseFragment
import dev.nmgalo.repo.presentation.utils.autoCleared
import javax.inject.Inject

@AndroidEntryPoint
class RepoDetailsFragment @Inject constructor(

) : BaseFragment<RepoViewModel>(R.layout.fragment_repo_details) {
    override val viewModel: RepoViewModel by viewModels()
    private var binding by autoCleared<FragmentRepoDetailsBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRepoDetailsBinding.bind(view)
        binding.onViewBind()
    }

    private fun FragmentRepoDetailsBinding.onViewBind() {
        toolbar.root.setNavigationOnClickListener { viewModel.popBackStack() }
    }
}
